
package binding;

public class Binding {
    public void method1(){
        System.out.println("Binding in Method 1 ");
    }
    public void method2(){
        System.out.println("Binding in Method 2");
    }
    
    public static void main(String[] args) {
        Binding obj = new Binding();
        obj.method();
        obj.method2();
        
        // TODO code application logic here
    }
    
}
