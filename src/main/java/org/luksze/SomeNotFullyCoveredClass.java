package org.luksze;

public class SomeNotFullyCoveredClass {
    public boolean complexCondition(String someText) {
        return someText != null && someText.length() > 5 && (someText.contains("o") || someText.contains("s"));
    }
}
