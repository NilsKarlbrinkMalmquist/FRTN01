/* library needed to include the int16_t and int32_t types.
it is needed also in the floating point controllers for the
definition of outputs and inputs. */
#include <inttypes.h>

//Defining all constants/controller parameters
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

//Define the variables x1, x2 and v as static floats, meaning they are ot the type float and their values are kept between different calls on the pos_fixed fucntion.
//Initialize them as zero.
static float x1 = 0;
static float x2 = 0;
static float v = 0;

int16_t pos_float(int16_t r, int16_t y){
    float u = kr*r - k1*x1 - k2*x2 - v; //Calculate the control signal u
    //Check if the signal is saturated to avoid overflow:
    if (u > 511) {  //If the tempor control signal is greater than 511, set the temporary control signal to 511
      u = 511; //Sets the 32 bit temporary control signal to 511 if ot was greater than that when calculated
    }
    else if (u < -512) { //If the tempor control signal is less than -512, set the temporary control signal to -512
      u = -512;  //Sets the 32 bit temporary control signal to -512 if ot was less than that when calculated
    }

    float x1_temp = x1; //stores the old value fof x1 in x1_temp to be able to calculate x2 with the old value of x1.
    float epsilon = y - x2; //Calculate epsilon
    //Precalculations of x1, x2 and v for the next sample period
    x1= phi11*x1 + phi12*x2 + gamma1*(u+v) + l1*epsilon;  //Calculate x1 for the next sample period
    x2 = phi21*x1_temp + phi22*x2 + gamma2*(u+v) + l2*epsilon;   //Calculate x2 for the next sample period
    v = v + lv*epsilon; //Calculate v for the next sample period



    return u; // write output to D/A and end function
}
