#ifndef clox_chunk_h
#define clox_chunk_h

#include "common.h"
#include "value.h"

typedef enum
{
  OP_CONSTANT,
  OP_RETURN
} Opcode;

typedef struct
{
  int count;
  int capacity;
  uint8_t *code;
  int *lines;
  ValueArray constants;
} Chunk;

void initChunk(Chunk *);
void writeChunk(Chunk *, uint8_t, int);
void freeChunk(Chunk *);
int addConstant(Chunk *chunk, Value value);

#endif