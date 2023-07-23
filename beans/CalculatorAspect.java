package beans;

import spring.Around;
import spring.Aspect;
import spring.Component;

@Component
@Aspect
public class CalculatorAspect {
    
    @Around
    public Object method() {
        return 100;
    }
}
