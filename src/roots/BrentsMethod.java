package roots;

import functions.Function;
import functions.FunctionArguments;
import gui.Graph;

public class BrentsMethod {
    public static RootFindResults brentsMethod(Function f, double b, double a, double stop, int max, Graph g) {
        
        double[] arg = new double[1];
        
        arg[0] = a;
        double fa = f.evaluate(new FunctionArguments(arg));
        arg[0] = b;
        double fb = f.evaluate(new FunctionArguments(arg));
        if(fa * fb >= 0) {
            return new RootFindResults(fb, max, fb, fb, false);
        }
        
        if(Math.abs(fa) < Math.abs(fb)) {
            // Swap low and high
            double temp = a;
            a = b;
            b = temp;
            
            // Swap f(low), f(high)
            temp = fa;
            fa = fb;
            fb = temp;
        }
        
        double c = a;
        double fc = fa;
        double d = c;
        double s = 0.0;
        double fs = 1.0;
        boolean mflag = true;
        while(fb != 0.0 && fs != 0.0 && Math.abs(a-b) > 0.001 * stop) {
            if(fa != fc && fb != fc) {
                // Perform inverse quadratic interpolation
                System.out.println("IQI");
                s = a*fb*fc/((fa-fb)*(fa-fc)) + b*fa*fc/((fb-fa)*(fb-fc)) + c*fa*fb/((fc-fa)*(fc-fb));
            } else {
                System.out.println("Using secant method");
                // Secant
                s = b - fb * (b-a) / (fb-fa);
            }
            
            if(!(inBetween(s,b,(3*a+b)/4))
                    || (mflag && Math.abs(s-b) >= Math.abs(b-c)/2)
                    || (!mflag && Math.abs(s-b) >= Math.abs(c-d)/2)
                    || (mflag && Math.abs(b-c) < stop) 
                    || (!mflag && Math.abs(c-d) < stop)) {
                System.out.println("Using Bisection");
                // Bisection
                s=(a+b)/2;
                mflag = true;
            } else {
                mflag = false;
            }
            arg[0] = s;
            fs = f.evaluate(new FunctionArguments(arg));
            d = c;
            c = b;
            if(fa*fs < 0) {
                b = s;
            } else {
                a = s;
            }
            
            arg[0] = a;
            fa = f.evaluate(new FunctionArguments(arg));
            arg[0] = b;
            fb = f.evaluate(new FunctionArguments(arg));
            if(Math.abs(fa) < Math.abs(fb)) {
                // Swap low and high
                double temp = a;
                a = b;
                b = temp;
                
                // Swap f(low), f(high)
                temp = fa;
                fa = fb;
                fb = temp;
            }
        }
        
        System.out.println("Root = " + s + " Eval= " + fs);
        
        return null;
    }
    
    private static boolean inBetween(double x, double end1, double end2) {
        return (x <= end2 && x >= end1) || (x >= end2 && x <= end1);
    }
}
