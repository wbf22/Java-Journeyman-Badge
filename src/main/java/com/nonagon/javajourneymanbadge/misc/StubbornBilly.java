package com.nonagon.javajourneymanbadge.misc;

import com.nonagon.javajourneymanbadge.clarice.ClariceBrain;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public final class StubbornBilly {

    private final boolean comeOut;

    public StubbornBilly(){
        comeOut = false;
    }

    public void askToComeOut() {
        ClariceBrain.printFile(ClariceBrain.BILLY_NO);
    }

    public boolean isComeOut() {
        return comeOut;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StubbornBilly billy = (StubbornBilly) o;
        return comeOut == billy.comeOut;
    }

    @Override
    public int hashCode() {
        return Objects.hash(comeOut);
    }

    @Override
    public String toString() {
        return "StubbornBilly{" +
                "comeOut=" + comeOut +
                '}';
    }

}
