/* PR 11207.  */
/* {{ dg-checkwhat "c-analyzer" }} */
char font8x8[256][8] = { [-1] = { 0 } }; /* {{ dg-error "array index|near init" }} */
