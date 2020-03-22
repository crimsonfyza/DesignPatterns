package sample.web.ui.domain;


public interface GradeState {
    void next(Grade pkg);
    void prev(Grade pkg);
    void printStatus();
}
