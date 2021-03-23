/* library needed to include the int16_t and int32_t types.
it is needed also in the floating point controllers for the 
definition of outputs and inputs. */
#include <inttypes.h>

#define K 2.6133   // examle of how parameters are defined in C
#define Ti 0.4523
#define beta 0.5
#define h 0.05

static float I = 0;

int16_t vel_float(int16_t r, int16_t y){

    /**********************************/
    // Implement your controller here //
    /**********************************/
    /*
    Use only int16_t variables for the fixed-point 
    implementation.
    */

    float u = (K*beta*r) - (K*y) + I;
    I = I + (K*h/Ti)*(r-y);

    return (int16_t) u; // write output to D/A and end function
}