package biseccion;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Procedimiento {
    Pantalla pantalla = new Pantalla();
    
    public Procedimiento() {}
    
    //documentacion libreria ScriptEngine --- JEP PARSER
    public static double f(String s, double x) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        engine.put("x", x); 
        engine.put("e", 2.71828182845904); 
        Object operation;
        String resultado = null;
        try {
            s = s.replace("pow", "Math.pow");
            operation = engine.eval(s);
            resultado = operation.toString();
        }
        catch (ScriptException ev) {
           
        }
        return Double.parseDouble(resultado);
    }
    
    public static String procedimiento(double x1, double xu, double errorDeseado, String s) {
        double xr, aux = 0;
        double errorActual;
        
        if(f(s, x1) * f(s, xu) > 0) return "Error!  f(x1) * f(xu) <= 0 No hay raiz";
        
        //saber si algun extremo del intervalo es una raiz
        if(f(s, x1) == 0) 
        return Double.toString(x1);
        if(f(s, xu) == 0)
        return Double.toString(xu);
        
        
        //metodo de biseccion
        do{
            xr = (x1 + xu) / 2;
            if(f(s, x1) * f(s, xr) < 0) xu = xr;
            if(f(s, x1) * f(s, xr) > 0) x1 = xr;
            if(f(s, x1) * f(s, xr) == 0) break;

            errorActual = ((xr - aux) / xr) * 100;
            if(errorActual < 0) errorActual *= -1;
            
            aux = xr;
        }while(errorActual > errorDeseado);
        
        return Double.toString(xr);
    }
    
}
