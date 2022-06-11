package eu.qped.java.checkers.design.feedback;

import java.util.*;

public final class DesignFeedbackGenerator {

    //Element Feedback
    public final static String WRONG_ELEMENT_NAME = "WrongElementName";
    public final static String WRONG_ELEMENT_TYPE = "WrongElementType";

    //Modifier Feedback
    public final static String WRONG_ACCESS_MODIFIER = "WrongAccessModifier";
    public final static String WRONG_NON_ACCESS_MODIFIER = "WrongNonAccessModifier";

    //Field Feedback
    public final static String MISSING_FIELDS = "MissingFields";
    public final static String FIELDS_NOT_RESTRICTIVE_ENOUGH = "FieldsNotRestrictiveEnough";

    //Method Feedback
    public final static String MISSING_METHODS = "MissingMethods";
    public final static String METHODS_NOT_RESTRICTIVE_ENOUGH = "MethodsNotRestrictiveEnough";

    //Class Feedback
    public final static String MISSING_INTERFACE_IMPLEMENTATION = "MissingInterfaceImplementation";
    public final static String MISSING_ABSTRACT_CLASS_IMPLEMENTATION = "MissingAbstractClassImplementation";
    public final static String MISSING_STATIC_CLASS_IMPLEMENTATION = "MissingStaticClassImplementation";
    public final static String MISSING_FINAL_CLASS_IMPLEMENTATION = "MissingFinalClassImplementation";
    public final static String MISSING_CLASS_IMPLEMENTATION = "MissingClassImplementation";
    public final static String WRONG_CLASS_TYPE = "WrongClassType";
    public final static String WRONG_CLASS_NAME = "WrongClassName";
    public final static String WRONG_INHERITED_CLASS_TYPE = "WrongInheritedClassType";
    public final static String WRONG_INHERITED_CLASS_NAME = "WrongInheritedClassName";
    public final static String DIFFERENT_INTERFACE_NAMES_EXPECTED = "DifferentInterfaceNamesExpected";
    public final static String DIFFERENT_CLASS_NAMES_EXPECTED = "DifferentClassNamesExpected";

    private static final String elementPlaceholder = "%NAME%";
    private static final String classPlaceholder = "%CLASSNAME%";

    private static final Map<String, String> FEEDBACK_MAP = createFeedbackMap();
    public static final Map<List<Boolean>, String> VIOLATION_CHECKS = createViolationMap();

    private DesignFeedbackGenerator() {}

    private static Map<String, String> createFeedbackMap() {
        Map<String, String> feedbackMap = new HashMap<>();

        feedbackMap.put(WRONG_ELEMENT_TYPE,
                "Element \""+elementPlaceholder+"\" in \""+classPlaceholder+"\"  does not possess the expected type.\n" +
                "Is the type of \""+elementPlaceholder+"\" set according to the task description?");

        feedbackMap.put(WRONG_ELEMENT_NAME,
                "Element \""+elementPlaceholder+"\" in \""+classPlaceholder+"\"  does not possess the expected name.\n" +
                "Is the name of \""+elementPlaceholder+"\"  set according to the task description?");
        feedbackMap.put(WRONG_ACCESS_MODIFIER,
                "Different access modifier for \""+elementPlaceholder+"\" in \""+classPlaceholder+"\" expected.\n" +
                "Are the access modifiers (e.g. public, private, protected, ...) of \""+elementPlaceholder+"\" set according to the task description?");
        feedbackMap.put(WRONG_NON_ACCESS_MODIFIER,
                "Different non access modifiers for \""+elementPlaceholder+"\" in \""+classPlaceholder+"\"  expected.\n" +
                "Are the non access modifiers (e.g. static, final, abstract, ...) of \""+elementPlaceholder+"\" set according to the task description?");
        feedbackMap.put(MISSING_FIELDS,
                "Expected fields in \""+classPlaceholder+"\" missing.\n" +
                "Do all fields, mentioned in the task description, exist in \""+classPlaceholder+"\"?");
        feedbackMap.put(FIELDS_NOT_RESTRICTIVE_ENOUGH,
                "Field access modifiers in \""+classPlaceholder+"\" are not restrictive enough.\n" +
                "Are all field access modifiers in \""+classPlaceholder+"\" set to be as restrictive as possible?");
        feedbackMap.put(MISSING_METHODS,
                "Expected methods in \""+classPlaceholder+"\" missing.\n" +
                "Do all methods, mentioned in the task description, exist in \""+classPlaceholder+"\"?");
        feedbackMap.put(METHODS_NOT_RESTRICTIVE_ENOUGH,
                "Method Access Modifiers in \""+classPlaceholder+"\" are not restrictive enough.\n" +
                "Are all method access modifiers in \""+classPlaceholder+"\" set to be as restrictive as possible?");
        feedbackMap.put(MISSING_INTERFACE_IMPLEMENTATION,
                "Expected interface implementation missing in \""+classPlaceholder+"\".\n" +
                "Has the interface, mentioned in the task, been implemented in \""+classPlaceholder+"\"?");
        feedbackMap.put(MISSING_ABSTRACT_CLASS_IMPLEMENTATION,
                "Expected abstract class extension missing in \""+classPlaceholder+"\".\n" +
                "Has the abstract class, mentioned in the task, been extended in \""+classPlaceholder+"\"?");
        feedbackMap.put(MISSING_STATIC_CLASS_IMPLEMENTATION,
                "Expected class extension missing in \""+classPlaceholder+"\".\n" +
                "Has the static class, mentioned in the task, been extended in \""+classPlaceholder+"\"?");
        feedbackMap.put(MISSING_FINAL_CLASS_IMPLEMENTATION,
                "Expected class extension missing in \""+classPlaceholder+"\".\n" +
                "Has the final class, mentioned in the task, been extended in \""+classPlaceholder+"\"?");
        feedbackMap.put(MISSING_CLASS_IMPLEMENTATION,
                "Expected class extension missing in \""+classPlaceholder+"\".\n" +
                "Has the class, mentioned in the task, been extended in \""+classPlaceholder+"\"?");
        feedbackMap.put(WRONG_CLASS_TYPE,
                "Different type for \""+classPlaceholder+"\" expected.\n" +
                "Is the type of \""+classPlaceholder+"\" set according to the task description?");
        feedbackMap.put(WRONG_CLASS_NAME,
                "Different name for \""+classPlaceholder+"\" expected.\n" +
                "Is the name of \""+classPlaceholder+"\" set according to the task description?");
        feedbackMap.put(WRONG_INHERITED_CLASS_TYPE,
                "Different inherited type for \""+elementPlaceholder+"\" in \""+classPlaceholder+"\" expected.\n" +
                "Does the inherited class \""+elementPlaceholder+"\" in \""+classPlaceholder+"\" have the class type set according to the task description?");
        feedbackMap.put(WRONG_INHERITED_CLASS_NAME,
                "Different inherited name for \""+elementPlaceholder+"\" in \""+classPlaceholder+"\" expected.\n" +
                "Does the inherited class \""+elementPlaceholder+"\" in \""+classPlaceholder+"\" have the name set according to the task description?");
        feedbackMap.put(DIFFERENT_INTERFACE_NAMES_EXPECTED,
                "Different inherited interface names in \""+classPlaceholder+"\" expected.\n" +
                "Do the implemented interfaces in \""+classPlaceholder+"\" have the name set according to the task description?");
        feedbackMap.put(DIFFERENT_CLASS_NAMES_EXPECTED,
                "Different inherited class names in \""+classPlaceholder+"\" expected.\n" +
                "Do the extended classes in \""+classPlaceholder+"\" have the name set according to the task description?");

        return Collections.unmodifiableMap(feedbackMap);
    }

    /*
      Format: (access, non access, type, name) for correctness
     */
    private static Map<List<Boolean>, String> createViolationMap() {
        Map<List<Boolean>, String> violationMap = new HashMap<>();
        violationMap.put(Arrays.asList(false, true, true, true), WRONG_ACCESS_MODIFIER);
        violationMap.put(Arrays.asList(false, true, true, false), WRONG_ACCESS_MODIFIER);
        violationMap.put(Arrays.asList(false, true, false, true), WRONG_ACCESS_MODIFIER);
        violationMap.put(Arrays.asList(false, false, true, true), WRONG_ACCESS_MODIFIER);
        violationMap.put(Arrays.asList(false, false, true, false), WRONG_ACCESS_MODIFIER);
        violationMap.put(Arrays.asList(false, false, false, true), WRONG_ACCESS_MODIFIER);

        violationMap.put(Arrays.asList(true, false, true, true), WRONG_NON_ACCESS_MODIFIER);
        violationMap.put(Arrays.asList(true, false, true, false), WRONG_NON_ACCESS_MODIFIER);
        violationMap.put(Arrays.asList(true, false, false, true), WRONG_NON_ACCESS_MODIFIER);

        violationMap.put(Arrays.asList(true, true, false, true), WRONG_ELEMENT_TYPE);
        violationMap.put(Arrays.asList(true, true, false, false), WRONG_ELEMENT_TYPE);

        violationMap.put(Arrays.asList(true, true, true, false), WRONG_ELEMENT_NAME);

        //Since we do not have enough info to determine if the fields are there anymore, we expect them to be missing
        violationMap.put(Arrays.asList(false, false, false, false), MISSING_FIELDS);
        violationMap.put(Arrays.asList(true, false, false, false), MISSING_FIELDS);
        violationMap.put(Arrays.asList(false, true, false, false), MISSING_FIELDS);

        return Collections.unmodifiableMap(violationMap);
    }

    private static String getFeedbackBody(String violationType) {
        return FEEDBACK_MAP.get(violationType);
    }

    public static DesignFeedback generateFeedback(String className, String elementName, String violationType) {
        String feedbackBody = violationType+": "+getFeedbackBody(violationType);
        feedbackBody = feedbackBody.replaceAll(classPlaceholder, className);
        feedbackBody = feedbackBody.replaceAll(elementPlaceholder, elementName);

        return new DesignFeedback(feedbackBody);
    }

}
