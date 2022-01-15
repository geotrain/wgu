package android.reserver.C868_greg_westmoreland.All.Entities;

public enum CourseStatusEntity {

    /**
     * In progress status converted to a string
     */
    IN_PROGRESS {
        @Override
        public String toString() {
            return "In Progress";
        }
    },

    /**
     * Completed status converted to a string
     */
    COMPLETED {
        @Override
        public String toString() {
            return "Completed";
        }
    },

    /**
     * Dropped status converted to a string
     */
    DROPPED {
        @Override
        public String toString() {
            return "Dropped";
        }
    },

    /**
     * Plan to take status converted to a string
     */
    PLAN_TO_TAKE {
        @Override
        public String toString() {
            return "Plan to take";
        }
    };

    /**
     * This method converts the courses status to a string
     * @param string
     * @return
     */
    public static CourseStatusEntity fromString(String string){
        for (CourseStatusEntity status: CourseStatusEntity.values()){
            if (status.toString().equalsIgnoreCase(string))
                return status;
        }
        return null;
    }
}
