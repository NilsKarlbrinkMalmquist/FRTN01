syms K Ti real;
s = -3 + 2i;
poles = s^2 + (0.12 + 2.25*K)*s + (2.25*K)/Ti == 0;
s1 = solve(poles, [K, Ti]);
K = s1.K
Ti = s1.Ti

syms K Ti real;
s=-3 - 2i;
poles = s^2 + (0.12 + 2.25*K)*s + (2.25*K)/Ti == 0;
s2 = solve(poles, [K, Ti]);
K = s2.K
Ti = s2.Ti 