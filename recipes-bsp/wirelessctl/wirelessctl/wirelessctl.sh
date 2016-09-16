#!/bin/sh

# wirelessctl -- Script which controls the diffrent applied wireless interfaces
#
# Copyright (C) sitec systems GmbH
#
# Author: Robert Lehmann <robert.lehmann@sitec-systems.de>

#set -x

# Constants
# Return codes
OK=0
NO_GSM_PIN=2
NO_GSMMUX=3
NOT_RUN=4
NO_VAL_ARG=5
FAIL_START=6
RUNNING=$OK

# Other constants
MODEM_PORT=/dev/ttyMODEMA0
DBG_GPIO=/sys/kernel/debug/gpio
SCRIPT=$0
GSM_DIR="/proc/device-tree/sitec/v1/device@1/equipment_category/gsm_module"
RUN_FILE="/var/run/wirelessctl.run"
CONFIG="/etc/wirelessctl.conf"
GSMMUX_CFG="/etc/gsmmux.conf"

# variables
ismobil=0 # default no gsm
iswifi=0 # default no wifi
wait_restart=1
wait_start=1
gsm_res=0
gsm_ign=0

msg() {
    logger $SCRIPT "$1: $2"
}

err() {
    msg "ERROR" $1
    echo "ERROR: $1"
}

info() {
    msg "INFO" "$1"
    echo "INFO: $1"
}

check_mobil() {
    if [ -d "$GSM_DIR" ]; then
        ismobil=1
    else
        ismobil=0
    fi
}

gsmmux_wait() {
    sed -i 's/wait=0/wait=1/' $GSMMUX_CFG
    sync
}

gsmmux_no_wait() {
    sed -i 's/wait=1/wait=0/' $GSMMUX_CFG
    sync
}

get_gsm_res() {
    if [ -f $DBG_GPIO ]; then
        gsm_res=$(cat $DBG_GPIO | grep gsm_res | sed 's/\s\+/ /g' | cut -d " " -f 2 | sed 's/gpio-//g')
    fi

    if [ $gsm_res -eq 0 ]; then
        if [ ! -f /sys/class/gpio/gpio$gsm_res/value ]; then
            err "No gsm reset line availible"
            exit $NO_GSM_PIN
        fi
    fi
}

get_gsm_ign() {
    if [ -f $DBG_GPIO ]; then
        gsm_ign=$(cat $DBG_GPIO | grep gsm_ign | sed 's/\s\+/ /g' | cut -d " " -f 2 | sed 's/gpio-//g')
    fi

    if [ $gsm_ign -eq 0 ]; then
        if [ ! -f /sys/class/gpio/gpio$gsm_ign/value ]; then
            err "No gsm ign line availible"
            exit $NO_GSM_PIN
        fi
    fi
}

modem_activate() {
    echo 0 > /sys/class/gpio/gpio$gsm_res/value
    usleep 200000 # sleep for 200 ms

    if [ $wait_start -eq 0 ]; then
        gsmmux_no_wait
    fi

    /etc/init.d/gsmmuxctl start > /dev/null
    if [ $? -eq 1 ]; then
        err "Can't start gsmmuxctl"
        exit $NO_GSMMUX
    fi
    if [ $wait_start -eq 0 ]; then
        gsmmux_wait
    else
        wait_for_mobile
    fi

    echo 1 > $RUN_FILE
}

modem_deactivate() {
    /etc/init.d/gsmmuxctl status > /dev/null
    if [ $? -eq 0 ]; then
        /etc/init.d/gsmmuxctl stop > /dev/null
        usleep 200000
    fi

    echo 0 > /sys/class/gpio/gpio$gsm_ign/value
    sleep 1
    echo 1 > /sys/class/gpio/gpio$gsm_ign/value

    sleep 1

    echo 1 > /sys/class/gpio/gpio$gsm_res/value

    rm -f $RUN_FILE
}

wait_for_mobile() {
    found=0
    for i in $(seq 60)
    do
        if [ ! -e $MODEM_PORT ]; then
            msg "Modem not ready"
            sleep 1
        else
            found=1
            break
        fi
    done

    if [ $found -eq 1 ]; then
        info "Modem is ready"
    else
        err "Can't start modem"
        exit $FAIL_START
    fi
}

read_config() {
    if [ -f $CONFIG ]; then
        . $CONFIG
    fi
}

do_start() {
    if [ -f $RUN_FILE ]; then
        info "Service is already running"
        exit $RUNNING
    fi
    if [ $ismobil -eq 1 ]; then
        info "Starting mobile service..."
        modem_deactivate
        sleep 1
        modem_activate
        if [ $wait_start -eq 1 ]; then
            wait_for_mobile
        fi
    fi
}

do_stop() {
    if [ $ismobil -eq 1 ]; then
        if [ ! -f $RUN_FILE ]; then
            info "Service is not running"
            exit $NOT_RUN
        fi
        info "Stopping mobile service..."
        modem_deactivate
        info "...Mobile servie is stopped"
    fi
}

do_status() {
    if [ -f $RUN_FILE ]; then
        info "Service is running"
        exit $RUNNING
    else
        info "Service is not running"
        exit $NOT_RUN
    fi
}

do_restart() {
    do_stop
    if [ $wait_restart -eq 0 ]; then
        gsmmux_no_wait
    fi

    do_start
    if [ $wait_restart -eq 1 ]; then
        wait_for_mobile
    fi

    if [ $wait_restart -eq 0 ]; then
        gsmmux_wait
    fi
    info "...Mobile service is started"
}

do_force_restart() {
    if [ $ismobil -eq 1 ]; then
        info "FORCE: Stopping mobile service..."
        modem_deactivate
        info "...Mobile servie is stopped"

        sleep 1

        info "FORCE Starting mobile service..."
        modem_activate
        if [ $wait_restart -eq 1 ]; then
            wait_for_mobile
        fi
        info "...Mobile service is started"
    fi
}

check_mobil
get_gsm_res
get_gsm_ign
read_config

case "$1" in
	start)
        do_start
        if [ $ismobil -eq 1 ]; then
            info "...Mobile service is started"
        fi
		;;
	stop)
        do_stop
		;;
	status)
        do_status
		;;
	restart)
        do_restart
		;;
    force-restart)
        do_force_restart
        ;;
	*)
		echo "Usage: $SCRIPT {start|stop|status|restart|force-restart}"
        echo "Return Values:"
        echo " $OK - Everything works fine"
        echo " $NO_GSM_PIN - No Reset line for mobile device availible"
        echo " $NO_GSMMUX - Error during the start of gsmmux"
        echo " $NOT_RUN - Service is not running"
        echo " $NO_VAL_ARG - Your given argument is not valid"
        echo " $FAIL_START - Can't start modem"
        echo " $RUNNING - Service is running"
        exit $NO_VAL_ARG
        ;;
esac

exit 0
