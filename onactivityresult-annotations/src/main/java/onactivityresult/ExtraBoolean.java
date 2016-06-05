package onactivityresult;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * parameters of a {@link OnActivityResult} annotated method can be annotated to get a boolean extra of the Intent<br>
 * Example:<br>
 * <p>
 * <code>
 *     &#64;OnActivityResult(requestCode = 1)
 *     void onResult(&#64;ExtraBoolean final boolean extraBoolean) {
 *          // Do something
 *     }
 * </code>
 * </p>
 * <br>
 * extra name: same as parameter <br>
 * NOTE: In this case it would be extraBoolean<br>
 * <br>
 * NOTE: If you don't care about the {@link ExtraBoolean#defaultValue()} you can also use the {@link Extra} annotation<br>
 * <br>
 *
 * @since 0.3.0
 */
@Retention(CLASS)
@Target(PARAMETER)
public @interface ExtraBoolean {
    /**
     * @return the set default value if the extra is not set on the intent
     * @since 0.3.0
     */
    boolean defaultValue() default false;

    /**
     * @return the name of the extra parameter which is contained in the Intent
     * @since 0.6.0
     */
    String name() default "";
}
