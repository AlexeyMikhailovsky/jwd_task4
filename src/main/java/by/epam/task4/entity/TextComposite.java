package by.epam.task4.entity;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent{

    private TextComponentType type;
    private List<TextComponent> components = new ArrayList<TextComponent>();

    public TextComposite(TextComponentType type) {
        this.type = type;
    }

    @Override
    public void add(TextComponent component) {
        components.add(component);
    }

    @Override
    public void remove(TextComponent component) {
        components.remove(component);
    }

    @Override
    public List<TextComponent> getComponents() {
        return components;
    }

    @Override
    public void setComponents(List<TextComponent> components) {
        if (components != null) {
            this.components.removeAll(this.components);
            this.components.addAll(components);
        }
    }

    @Override
    public TextComponentType getType() {
        return type;
    }

    @Override
    public int size() {
        return components.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String delimiter = type.getDelimiter();
        for (TextComponent textComponent : components) {
            sb.append(" " + type + " [")
                    .append(textComponent.toString())
                    .append("]")
                    .append(delimiter);
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 11;
        result = result * components.hashCode();
        result = result * type.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextComposite text = (TextComposite) o;
        return components.equals(text.components) &&
                (type != null ? type == text.type : text.type == null);
    }
}
