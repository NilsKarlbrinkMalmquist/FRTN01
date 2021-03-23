/* library needed to include the int16_t and int32_t types.
it is needed also in the floating point controllers for the
definition of outputs and inputs. */
#include <inttypes.h> //include inttypes.h headerfile
//Empty line
#define K 21408                                         //defining K converted to fixed-point
#define Ti 0.4523                                       //defining Ti converted to fixed-point
#define beta 0.5                                        //defining beta
#define h 0.05                                          //defining h
#define Kb 10704                                        //defining K*beta coverted to fixed-point
#define Kh_Ti 2367                                      //defining K*beta/Ti converted to fixed-point
#define n 13                                            //defining number of fractional bits (n)
//Empty line
static int16_t I;                                       //declaring I as a static 16-bit integer
static int16_t u;                                       //deklaring u as a static 16-bit integer
                                                        //Empty line
int16_t vel_fixed(int16_t r, int16_t y){                //function vel_fixed
                                                        //Empty line
    /**********************************/                //comment
    // Implement your controller here //                //comment
    /**********************************/                //comment
    /*                                                  //comment
    Use only int16_t variables for the fixed-point      //comment
    implementation.                                     //comment
    */                                                  //comment
                                                        //Empty line
    int32_t u_temp = (int32_t)((Kb*r) - (K*y) + (I << n)) >> n;
    if(u_temp > 511){
        u_temp = 511;
    }
    else if(u_temp < -512){
        u_temp = -512;
    }
    u = (int16_t)u_temp;

    int32_t I_temp = (int32_t)((I << n) + (Kh_Ti)*(r-y)) >> n;
    I = (int16_t)I_temp;

    return u; // write output to D/A and end function
}
