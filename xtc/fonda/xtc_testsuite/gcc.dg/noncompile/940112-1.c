/* {{ dg-checkwhat "c-parser" }} */
double
f (int x)
{
  double e = 1;
  e = 1;
  return (e)
    }	/*  {{ dg-error "expected" }} */
