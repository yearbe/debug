package app.y_vistor.computer;

public interface ComputerPart {
    void accept(ComputerPartVisitor computerPartVisitor);
}