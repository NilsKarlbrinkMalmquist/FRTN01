A = [-0.12 0; 5 0];
B = [2.25; 0]
C = [0 1];
D = 0;
h = 0.05;
I = eye(2);
K = [k1, k2];
t=h;
syms s k1 k2 real;

%Calculate phi:
%A^n will never become 0, therefore the first formula in the formula sheet to calculate phi with will not work. Use the second formula
%in the formula sheet instead.
phi = ilaplace(inv(s*I-A))

%syms x;
%Calculate gamma:
%fun = exp(A*x)
%Fun = matlabFunction(fun);
gamma = int(exp(A*s), 0, h)*B

%Calculate k1 & k2:
z = 0.8 + 0.1i;
%z = 0.8 - 0.1i


func = det(z*I-phi+gamma*K) == 0;
s1 = solve(func, [k1, k2]);
k1 = s1.k1
k2 = s1.k2