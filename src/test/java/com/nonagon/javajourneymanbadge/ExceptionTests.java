package com.nonagon.javajourneymanbadge;

import java.io.IOException;

import com.nonagon.javajourneymanbadge.trys.Trys;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExceptionTests {


  @Test
  public void testExceptionsInTrys(){
    Trys trys = new Trys();
    Assertions.assertThrows(NullPointerException.class, () -> { trys.NullPointer();} );
    Assertions.assertThrows(IOException.class, () -> { trys.IO();} );
  }


}
