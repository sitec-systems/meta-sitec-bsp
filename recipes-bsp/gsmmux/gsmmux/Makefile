# Comment/uncomment the following line to disable/enable debugging
DEBUG ?= x

TARGET = gsmmux
SRC = gsm0710.c buffer.c
OBJS = gsm0710.o buffer.o

CFLAGS = -Wall
LDLIBS = -lm -lc

ifeq ($(DEBUG),y)
  CFLAGS += -DDEBUG
endif


all: $(TARGET)

clean:
	rm -f $(OBJS) $(TARGET)

%.o: %.c
	$(CC) $(CFLAGS) -c -o $@ $<

$(TARGET): $(OBJS)
	$(CC) $(LDLIBS) -o $@ $(OBJS) $(LDFLAGS)

.PHONY: all clean
