/* library needed to include the int16_t and int32_t types.
it is needed also in the floating point controllers for the
definition of outputs and inputs. */
#include <inttypes.h>

//Define all constants/controller parameters
#define K 21408
#define Ti 0.4523
#define beta 0.5
#define h 0.05
#define Kb 10704  //defining K*beta coverted to fixed-point
#define Kh_Ti 2367 //defining K*beta/Ti converted to fixed-point
#define n 13  //defining number of fractional bits (n) as 13

//Declaring the integral part I as a 16 bit static static meaning it value are kept between different calls on the pos_fixed fucntion and that it can hold up to 16 bit values
//Initialize I as 0.
static int16_t I=0;

int16_t vel_fixed(int16_t r, int16_t y){
    int32_t u_temp = ((int32_t)(Kb*r) - (int32_t)(K*y) + (int32_t)(I << n)) >> n; //Create a temporary control signal u that can store 32 bit values. Also cast the 16 bit parameters to 32 when multiplying to avoid overflow
    //Check if the signal is saturated to avoid overflow:
    if (u_temp > 511) { //If the tempor control signal is greater than 511, set the temporary control signal to 511
      u_temp = 511; //Sets the 32 bit temporary control signal to 511 if ot was greater than that when calculated
    }
    else if (u_temp < -512) { //If the tempor control signal is less than -512, set the temporary control signal to -512
      u_temp = -512;  //Sets the 32 bit temporary control signal to -512 if ot was less than that when calculated
    }
    int16_t u = (int16_t)u_temp;  //Assigning to the control signal u as well as recasting the value from 32 to 16 bits

    int32_t I_temp = ((int32_t)(I << n) + (int32_t)(Kh_Ti)*(r-y)) >> n;  //Calculating a temporary Integral part I_temp with the type int32_t so that it can store 32 bit values
    I = (int16_t)I_temp;  //Assigning the value of I_temp to I as well as recasting the value from 32 to 16 bits

    return u; // write output to D/A and end function
}
