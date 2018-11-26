package packt.java.spring.mvc.dreamcar.interfaces;

import java.util.List;

import packt.java.spring.mvc.dreamcar.pojo.Component;

public interface IComponentService {
	public List<Component> getComponentList();
	public Component createComponent(String componentName);
}
