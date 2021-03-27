/* library needed to include the int16_t and int32_t types.
it is needed also in the floating point controllers for the
definition of outputs and inputs. */
#include <inttypes.h>

#define k1 3.2898
#define k2 1.7831
#define kr 1.7831
#define l1 2.0361
#define l2 1.2440
#define lv 3.2096
#define phi11 0.9940
#define phi12 0
#define phi21 0.2493
#define phi22 1.0000
#define gamma1 0.1122
#define gamma2 0.0140

static float x1 = 0;
static float x2 = 0;
static float v = 0;

int16_t pos_float(int16_t r, int16_t y){
    float x1_temp = x1;
    float u = kr*r - k1*x1 - k2*x2 - v;
    float epsilon = y - x2;
    x1= phi11*x1 + phi12*x2 + gamma1*(u+v) + l1*epsilon;
    x2 = phi21*x1_temp + phi22*x2 + gamma2*(u+v) + l2*epsilon;
    v = v + lv*epsilon;

    if (u > 511) {
      u = 511;
    }
    else if (u < -512) {
      u = -512;
    }

    return (uint16_t) u; // write output to D/A and end function
}
