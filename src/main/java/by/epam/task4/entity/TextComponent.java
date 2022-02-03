package by.epam.task4.entity;

import java.util.List;

public interface TextComponent {

    void add(TextComponent component);
    void remove(TextComponent component);
    List<TextComponent> getComponents();
    void setComponents(List<TextComponent> components);
    TextComponentType getType();
    int size();
    boolean equals(Object o);
    int hashCode();
    String toString();
}
