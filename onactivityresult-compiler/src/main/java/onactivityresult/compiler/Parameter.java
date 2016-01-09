package onactivityresult.compiler;

import java.util.List;

import javax.lang.model.element.AnnotationMirror;

final class Parameter {
    static final String RESULT_CODE = "resultCode";
    static final String INTENT      = "intent";
    static final String INTENT_DATA = "intentData";

    private PreCondition mPrecondition = PreCondition.DEFAULT;

    static Parameter createResultCode() {
        return new Parameter(RESULT_CODE, false);
    }

    static Parameter createIntent() {
        return new Parameter(INTENT, false);
    }

    static Parameter createIntentData() {
        return new Parameter(INTENT_DATA, true);
    }

    private final String name;

    private final boolean isIntentData;

    private Parameter(final String name, final boolean isIntentData) {
        this.name = name;
        this.isIntentData = isIntentData;
    }

    String getName() {
        return name + mPrecondition.getSuffix();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Parameter parameter = (Parameter) o;

        return !(name != null ? !name.equals(parameter.name) : parameter.name != null);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    boolean isIntentData() {
        return isIntentData;
    }

    public PreCondition getPreCondition() {
        return mPrecondition;
    }

    public void setPreCondition(final PreCondition preCondition) {
        mPrecondition = preCondition;
    }

    enum PreCondition {
        DEFAULT(""), NONNULL("NonNull"), NULLABLE("Nullable");

        static PreCondition from(final List<? extends AnnotationMirror> annotationMirrors) {
            for (final AnnotationMirror annotationMirror : annotationMirrors) {
                final String[] parts = annotationMirror.toString().split("\\.");

                if (parts.length > 0) {
                    final String last = parts[parts.length - 1];

                    if ("NotNull".equals(last) || "NonNull".equals(last)) {
                        return NONNULL;
                    }

                    if ("Nullable".equals(last)) {
                        return NULLABLE;
                    }
                }
            }

            return DEFAULT;
        }

        private final String suffix;

        PreCondition(final String suffix) {
            this.suffix = suffix;
        }

        String getSuffix() {
            return suffix;
        }
    }
}
