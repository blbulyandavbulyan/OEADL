package org.blbulyandavbulyan.oeadl.factories.exceptions;

import org.blbulyandavbulyan.oeadl.exceptions.OEADLException;

import java.util.ResourceBundle;

public class UiTextRbDoesntContainRequireKey extends OEADLFactoryException {
    private final String causeKey;
    private final ResourceBundle causeResourceBundle;
    public UiTextRbDoesntContainRequireKey(String causeKey, ResourceBundle causeResourceBundle) {
        super(String.format("ResourceBundle: \"%s\" doesn't contain key: \"%s\"", causeResourceBundle.getBaseBundleName(), causeKey));
        this.causeKey = causeKey;
        this.causeResourceBundle = causeResourceBundle;
    }

    public String getCauseKey() {
        return causeKey;
    }

    public ResourceBundle getCauseResourceBundle() {
        return causeResourceBundle;
    }
}
