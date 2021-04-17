/* library needed to include the int16_t and int32_t types.
it is needed also in the floating point controllers for the
definition of outputs and inputs. */
#include <inttypes.h>

//Define all constants/controller parameters
#define K 2.6133
#define Ti 0.4523
#define beta 0.5
#define h 0.05

//Declaring the integral part I as static meaning it value are kept between different calls on the pos_fixed fucntion
//Initialize I as 0.
static float I = 0;

int16_t vel_float(int16_t r, int16_t y){
    float u = (K*beta*r) - (K*y) + I; //Calculating the control signal u with the type float
    if (u > 511) { //If the tempor control signal is greater than 511, set the temporary control signal to 511
      u = 511; //Sets the 32 bit temporary control signal to 511 if ot was greater than that when calculated
    }
    else if (u < -512) { //If the tempor control signal is less than -512, set the temporary control signal to -512
      u = -512;  //Sets the 32 bit temporary control signal to -512 if ot was less than that when calculated
    }
    I = I + (K*h/Ti)*(r-y); //Calculate the Integral part I
    return u; // write output to D/A and end function
}
