package com.akoya.codex.upgrader;

public final class Messages {

    public static final String TITLE_MOUNT_POINTS = "Mount points";
    public static final String SMB_CONNECTION_LOST = "Connection to the share has been lost. Please remount";
    public static final String SHARED_DRIVE_ALREADY_MOUNTED = "The shared volume has been already mounted to the selected folder. \nDo you wish to proceed without any changes?";
    public static final String TITLE_CONFIRMATION = "Confirmation";
    public static final String MOUNTED_SUCCESSFULLY = "Mounted successfully!";
    public static final String MOUNTING_FAILED_ERROR_MESSAGE = "Mounting was failed. Please try again or choose another experiment";
    public static final String CONFIRM_TO_PROCEED_WITH_MISSED_FCS_FILES = "Couldn't read FCS file for region(s) [%s].\nDo you wish to proceed?";
    public static final String INVALID_POPULATION_SIZE = "Invalid size. Size should be from 1 to 20";

    private Messages() {
    }

    public static final String EMPTY_SIZE_FIELD = "Size field can't be empty!";
    public static final String INVALID_NUMBER_FORMAT = "Please enter valid numbers!";
    public static final String INVALID_BOUNDING_BOX = "The bounding box of the scatter chart should include all drawn gates!";

    public static final String INVALID_RANGE_FOR_TRANSPARENCY = "Invalid range for marker transparency!";
    public static final String UPPER_SHOULD_BE_GREATER_THAN_LOWER = "Upper should be greater than lower!";

    public static final String NO_SELECTED_MARKERS = "No selected markers";
    public static final String TITLE_MARKERS_FOR_CLUSTERING = "Markers for clustering";
    public static final String NO_CHANNEL = "Some channels are not listed in the 'channelNames.txt' file!";
    public static final String FEEDBACK_MESSAGE = "Thank you for feedback!";
    public static final String BOX_PLOT_WAS_SAVED = "The box plot was successfully saved!";

    public static final String TITLE_WARNING = "Warning";
    public static final String TITLE_INFORMATION = "Information";
    public static final String QUESTION_ENABLE_OFFLINE_MODE = "Are you sure you would like to enable offline mode?";
    public static final String TITLE_OFFLINE_MODE = "Offline mode";
    public static final String QUESTION_ARE_YOU_SURE = "Are you sure?";
    public static final String TITLE_CLOSE_APPLICATION = "Close application";

    public static final String POPULATION_WAS_ALREADY_ADDED = "Population was already added";
    public static final String EMPTY_GATE_PLOT = "Can not create child plot from empty gate";
    public static final String EMPTY_GATE_POPULATION = "Can not create population from empty gate";
    public static final String PLOT_CREATED = "A scatter chart already exists. If you wish to proceed, please delete the scatter chart and try again.";
    public static final String CLOSE_CHILD_PLOT = "Please close the created child scatter chart";
    public static final String LOCKED_AXIS = "You may not change the axes because a child scatter plot exists. If you wish to proceed, please delete the child scatter plot.";
    public static final String LOCKED_REGION = "You may not change the region because a child scatter plot exists. If you wish to proceed, please delete the child scatter plot.";
    public static final String GATES_WILL_DISAPPEAR = "Do you wish to proceed?";
    public static final String EXPERIMENT_REQUIRE_UPGRADING = "This experiment may require upgrading.";
    public static final String WRONG_EXPERIMENT_FOLDER = "Selected directory doesn't contain Experiment.json file.";
    public static final String BEST_FOCUS_FOLDER_NOT_PRESENT = "Selected directory doesn't contain Best focus folder.";

    public static final String NO_SEGMCONFIG_FOUND = "Selected directory doesn't contain segmentation config.txt file.";
    public static final String LOGGER_ERROR = "An error occurred while setting the log folder.";
    public static final String ERROR_DURING_OPEN_REGION = "An error occurred while opening the region.";
    public static final String ERROR_DURING_PARSE_EXPERIMENT = "An error occurred while parsing the 'experiment.json' file.";
    public static final String ERROR_DURING_ADDING_CLUSTERING_RUN = "An error occurred while adding a clustering run.";
    public static final String ERROR_DURING_OPEN_CLUSTERING_FCS = "An error occurred while opening FCS file from selected clustering run folder.";
    public static final String ERROR_DURING_SELECT_EXPERIMENT = "An error occurred while selecting the experiment.";
    public static final String NO_FCS_FILES = "No FCS files found. Please select another folder.";
    public static final String NO_FCS_FILES_MATCHES_CONVENTION = "No FCS files matching convention " +
            "<regXXX_file-name.fcs> were found. Please select another folder.";
    public static final String DUPLICATE_FCS_FILES = "There are duplicate FCS files for {%d} region.";
    public static final String NO_FCS_FOR_REGION = "There is no FCS file for {%d} region.";
    public static final String DUPLICATE_CLUSTERING_RUN_NAMES = "A folder with this name already exists, please rename and try again.";
    public static final String REGION_ALREADY_OPENED = "This region has already been opened.";

    public static final String ERROR_DURING_LOADING_CLUSTERS = "An error occurred while loading clusters.";
    public static final String ERROR_DURING_RESTORE_STATE = "An error occurred while restoring of analysis state.";

    public static final String STATE_RELATED_TO_ANOTHER_EXPERIMENT = "This state file is related with another experiment.";
    public static final String ERROR_DURING_OPEN_STATE = "Can't open state.";
    public static final String NEED_TO_SELECT_JSON = "Please select .json file.";

    public static final String ERROR = "Error";
    public static final String VORONOI_DIAGRAM_COMPLETE_MESSAGE = "Voronoi diagram saved";
    public static final String VORONOI_DIAGRAM_RENDERED = "Voronoi diagram rendered successfully!";
    public static final String WARNING_CREATE_POPULATION = "Please, create populations";
    public static final String CHOOSE_FOLDER = "Choose the folder";

    public static final String NO_POPULATIONS = "No populations for calculating S. N. Graph";
    public static final String POPULATION_EXISTS = "Population with such cells already exists!";
    public static final String SELECT_TWO_POPULATIONS = "Please select at minimum 2 populations.";
    public static final String NO_SELECTED_POPULATIONS = "Please select at minimum 1 population.";
    public static final String MIN_CELLS_COUNT_TO_CLUSTER = "Selected populations should contain at least 3 cells!";
    public static final String NEED_SOME_TIME_FOR_COMPUTATION = "Please wait, extended time needed for computation!";

    public static final String DENDROGRAM_SAVED = "Dendrogram saved.";
    public static final String SPATIAL_NETWORK_GRAPH_SAVED = "Spatial Network Graph was successfully saved!";
    public static final String GATING_HIERARCHY_SAVED = "Gating hierarchy was successfully saved!";

    public static final String IRREVERSIBLE_OPERATIONS = "Some operations were restored from analysis state and they are irreversible.";

    public static final String MIN_VISIBLE_MARKERS_COUNT_REACHED = "The minimum number of visible markers is 2!";

    public static final String WRONG_K_VALUE = "K value should be positive integer!";
    public static final String WRONG_NUMBER_OF_INTERACTIONS = "Number of interactions should be positive integer!";

    public static final String VORONOI_DIAGRAM_HASNOT_CREATED = "Voronoi diagram hasn't created yet!";

    public static final String INVALID_CODEX_SERVER = "Codex Server url is invalid!";
    public static final String INVALID_EMAIL_OR_PASS = "Invalid email or password";
    public static final String INVALID_URI = "Please, set Codex Server url matching 'protocol//host:port' pattern.";
    public static final String WARN_LOCAL_EXP = "Can not obtain experiments from the server. Please, choose experiment locally.";
    public static final String NO_PROCESSING_FOLDER_MATCHED = "No processing folder matched. Please, choose another experiment.";
    public static final String NO_PROCESSING_JOBS_ON_SERVER = "No processing jobs for such experiment on server found.";
    public static final String NO_PROCESSING_JOBS = "No processing jobs for such experiment on computer found.";
    public static final String NO_SEGMENTATION_JOBS = "No segmentation jobs for such experiment found.";
    public static final String NO_EXPERIMENT_ON_SERVER = "Can not obtain selected experiment information from the server!";
    public static final String NO_EXPERIMENT_ON_COMPUTER = "Selected experiment was not found on the computer!";

    public static final String SELECT_AT_LEAST_1_MARKER = "Please, select at least 1 marker.";
    public static final String WARN_NO_STATE = "You will lose some results because current analysis state wasn't saved.";
    public static final String[] COPYRIGHT_CONTENT = {"All Rights Reserved.", "CodexMAV is registered trademark of Akoya Biosciences, Inc.", "A Delaware corporation."};
    public static final String ONLINE_HELP = "Online help";
    public static final String FEEDBACK = "Feedback";
    public static final String ABOUT = "About";
    public static final String HELP = "Help";
    public static final String COPYRIGHT_2018 = "Copyright © 2018-2019";

    /**
     * Tooltip texts for buttons
     */
    public static class Tooltips {
        public static final String CLUSTER_POPULATIONS_USING_DND = "Cluster populations using dendrograms";
        public static final String EXPORT_POPULATIONS = "Export populations";
        public static final String CLUSTER_POPULATIONS_X_SHIFT = "Cluster populations using X-Shift";
        public static final String COMBINE_POPULATIONS = "Combine populations";
        public static final String DELETE_GATE = "Delete gate";
        public static final String CREATE_POPULATION = "Create population";
        public static final String CREATE_CHILD_PLOT = "Create child plot";
        public static final String CLIPBOARD = "Copy to the clipboard";
        public static final String EXPORT_THE_SCATTER_CHART = "Export the scatter chart";
        public static final String BOX_PLOT = "Refresh box plot";
        public static final String SAVE = "Save";
        public static final String CREATE_POPULATIONS = "Create population(s)";
        public static final String SELECT_ALL = "Select all";
        public static final String SELECT_COLUMN = "Select column";
        public static final String RETURN_TO_SELECTED_OPERATION = "Return to the selected operation";
        public static final String TOGGLE_OPERATIONS_WITH_EMPTY_COMMENTS = "Toggle operations with empty comments";
        public static final String OVERLAY_OPACITY = "Overlay opacity";
        public static final String RETURN_TO_DEFAULT_SORT_ORDER = "Return to default sort order";
        public static final String MOVE_UP = "Move up";
        public static final String MOVE_DOWN = "Move down";
        public static final String DELETE_POPULATION = "Delete population";
        public static final String CHOOSE_LOG_FOLDER = "Pick log folder...";
        public static final String CHOOSE_CODEX_ROOT_FOLDER = "Pick Codex root folder...";
        public static final String RESET_SCALES = "Reset scales";
        public static final String SCALE = "Scale";
        public static final String RESET_TITLE_TO_DEFAULT = "Reset title to default";
        public static final String CANCEL = "Cancel";
        public static final String APPLY_CHANGES = "Apply changes";
        public static final String RESET_TO_MIN_AND_MAX = "Reset to min and max";
        public static final String AUTO_ADJUST = "Auto-adjust";
        public static final String SETTINGS = "Settings";
        public static final String CHOOSE_MOUNT_POINT = "Choose a mount point for the current share %s";
    }
}
