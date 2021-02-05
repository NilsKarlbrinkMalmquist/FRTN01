%E0.1 :
% A = [0 1; 1 0];
% B = [1; 0];
% C = [0 1];
% D = [0];
% eig_A = eig(A)

%---------------------------

%E0.2:
% A = [0 1; 1 0];
% B = [1; 0];
% C = [0 1];
% D = [0];
% sys_dc = ss(A, B, C, D)
% sys_tf = tf(sys_dc)
%E0.3:
% sys_zpk = zpk(sys_dc)

%----------------------

%E0.4:
%a)
% s = tf('s');
% G = 1/(s^2 + 0.6*s + 1)*exp(-1.5*s)
% G.InputDelay = 0.5;
% get(G);
%b)
% bode(G);
% nyquist(G);

%The nyquist curve encirkles the critical point -1, which means that the
%closed-loop system will be unstable.

%----------------------

% %E0.5:
% A = [0 1; 1 0];
% B = [1; 0];
% C = [0 1];
% D = [0];
% 
% p = [1 1.4 1];
% K = place(A, B, roots(p))
% 
% %E0.6:
% p = [1 2.8 4];
% L = place(A', C', roots(p))
% 
% %----------------------------
% 
% %E0.7:
% Gp = 1/(s^2 + 0.6*s + 1);
% K = 2;
% Ti = 1.5;
% Td = 0.5;
% Gc = K*(1 + 1/(s*Ti) + s*Td);
% bode(Gc); % Bode diagram of controller
% margin(Gp*Gc) % Bode diagram of loop transfer function
% 
% %E0.8:
% Gcl = feedback(Gp*Gc, 1)
% Gcl = minreal(feedback(Gp*Gc, 1))
% Gcl = Gp*Gc/(1 + Gp*Gc)
% 
% %E0.9: Plot the step response of the closed-loop system. What is the stationary gain
% %from uc to y (use dcgain)?
% 
% step(Gcl)
% dcgain(Gcl)

%--------------------------

%Discrete-time systems:

%E0.9:
A = [1 1; 0 0];
B = [0 -1; 1 0];
C = [1 0];
D = [0];
H = ss(A, B, C, D)
step(H)
pole(H)

%For discrete-time systems, a pole in the origin represents a time delay of one
%step, while a pole in 1 represents an integrator.











