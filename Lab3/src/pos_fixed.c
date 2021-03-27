/* library needed to include the int16_t and int32_t types.
it is needed also in the floating point controllers for the
definition of outputs and inputs. */
#include <inttypes.h>

//Define all constants as fixed-point representations. 13 fractional bits are used.
//A fixed-point representation is calculated by multiplying the floating point value with
//2^n where n = nbr of fractional bits, in this case 13.
#define k1 26950
#define k2 14607
#define kr 14607
#define l1 16680
#define l2 10191
#define lv 26293
#define phi11 8143
#define phi12 0
#define phi21 2042
#define phi22 8192
#define gamma1 919
#define gamma2 115

//Define the number of fractional bits:
#define n 13

static int16_t x1 = 0;
static int16_t x2 = 0;
static int16_t v = 0;

int16_t pos_fixed(int16_t r, int16_t y){

    int32_t u_temp = ((int32_t)(kr*r) - (int32_t)(k1*x1) - (int32_t)(k2*x2) - (int32_t)(v << n)) >> n;
    if (u_temp > 511) {
      u_temp = 511;
    }
    else if (u_temp < -512) {
      u_temp = -512;
    }
    int16_t u = (int16_t)u_temp;

    int16_t x1_temp = x1;
    int16_t epsilon = y - x2;
    x1 = ((int32_t)(phi11*x1) + (int32_t)(phi12*x2) + (int32_t)(gamma1*(u+v)) + (int32_t)(l1*epsilon)) >> n;
    x2 = ((int32_t)(phi21*x1_temp) + (int32_t)(phi22*x2) + (int32_t)(gamma2*(u+v)) + (int32_t)(l2*epsilon)) >> n;
    v = ((int32_t)(v << n) + (int32_t)(lv*epsilon)) >> n;

    return u;
}
