package android.reserver.c196_greg_westmoreland.All.Entities;

public enum AssessmentTypeEntity {
    /**
     * This is the assessment type entity for performance assessment
     */
    Perf_Assessment {
        @Override
        public String toString() {
            return "Performance Assessment";
        }
    },

    /**
     * This is the assessment type entity for objective assessment
     */
    Obj_Assessment {
        @Override
        public String toString() { return "Objective Assessment"; }
    }
}
