package com.alvindizon.binlookup.features.lookup;

public class UIState {

    enum Status {
        LOADING, SUCCESS, ERROR
    }

    private static UIState INSTANCE;
    private String errorMessage;
    private Status status;
    private BinInfo binInfo;

    private UIState(Status status) {
        this.status = status;
    }

    private UIState(Status status, String errorMessage) {
        this(status);
        this.errorMessage = errorMessage;
    }

    private UIState(Status status, BinInfo binInfo) {
        this(status);
        this.binInfo = binInfo;
    }

    public static UIState LOADING() {
        INSTANCE = new UIState(Status.LOADING);
        return INSTANCE;
    }

    public static UIState ERROR(String errorMessage) {
        INSTANCE = new UIState(Status.ERROR, errorMessage);
        return INSTANCE;
    }

    public static UIState SUCCESS(BinInfo binInfo) {
        INSTANCE = new UIState(Status.SUCCESS, binInfo);
        return INSTANCE;
    }

    public Status getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public BinInfo getBinInfo() {
        return binInfo;
    }
}
