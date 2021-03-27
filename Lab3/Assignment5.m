A = [-0.12 0; 5 0];
B = [2.25; 0];
C = [0 1];
D = 0;
h = 0.05;
I = eye(2);
poles = [0.8 + 0.1i, 0.8 - 0.1i];
syms s k1 k2 real;

%Create a system representing the continous-time state-space model
G = ss(A,B,C,D)

%Convert the continous-time dynamic system to discrete time
H = c2d(G,0.05)

%Calculate Phi and Gamma:
Phi = H.A;
Gamma = H.B;

%Compute the K-matrix. Use place that computes a state-feedback matrix K
K = place(Phi,Gamma,poles)

%Compute kr
kr = 1/(C*((I-Phi+Gamma*K)\Gamma))

%--------------------------------------------------------------

%Assignment 6:
Phi_e = [Phi, Gamma; 0, 0, 1];

Gamma_e = [Gamma; 0];

C_e = [0,1,0];

observer_poles = [0.6 + 0.2i, 0.6 - 0.2i, 0.55];

L_e = place(Phi_e',C_e',observer_poles)

