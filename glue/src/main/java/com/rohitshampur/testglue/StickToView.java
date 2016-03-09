package com.rohitshampur.testglue;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
public @interface StickToView {
  /**
   * The R.id.* field which refers to the injected View.
   *
   * @return the id of the View
   */
  int value() default ResId.DEFAULT_VALUE;

  /**
   * The resource name which refers to the injected View.
   *
   * @return the resource name of the View
   */
  String resName() default "";
}
