package by.epam.task4.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SymbolLeaf implements TextComponent{

    static Logger logger = LogManager.getLogger();

    private TextComponentType componentType;
    private char symbol;

    public SymbolLeaf() {
    }

    public SymbolLeaf(TextComponentType componentType, char symbol) {
        this.symbol = symbol;
        this.componentType = componentType;
    }

    @Override
    public void add(TextComponent component) {
        logger.error("Not available operation add component");
        throw new UnsupportedOperationException("Not available operation add component");
    }

    @Override
    public void remove(TextComponent component) {
        logger.error("Not available operation remove component");
        throw new UnsupportedOperationException("Not available operation remove component");
    }

    @Override
    public List<TextComponent> getComponents() {
        logger.error("Not available operation get component");
        throw new UnsupportedOperationException("Not available operation get component");
    }

    @Override
    public void setComponents(List<TextComponent> components) {
        logger.error("Not available operation set component");
        throw new UnsupportedOperationException("Not available operation set component");
    }

    @Override
    public TextComponentType getType() {
        return componentType;
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SymbolLeaf)) return false;
        SymbolLeaf leaf = (SymbolLeaf) o;
        return symbol == leaf.symbol && (componentType != null ?
                componentType == leaf.componentType :
                leaf.componentType == null);
    }

    @Override
    public int hashCode() {
        int result = 11;
        result = result * Character.hashCode(symbol);
        result = result + (componentType != null ? componentType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return Character.toString(symbol);
    }
}
