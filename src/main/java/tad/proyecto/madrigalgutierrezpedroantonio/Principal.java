package tad.proyecto.madrigalgutierrezpedroantonio;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import javax.servlet.annotation.WebServlet;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("tad.proyecto.madrigalgutierrezpedroantonio.MyAppWidgetset")
public class Principal extends UI{
    
    private VerticalLayout content;
    private GridLayout row1;
    private Label bienven;
    private GridLayout row2;
    private Button login;
    private Button registro;
    
    public Principal(){
        
    }
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        getPage().setTitle("GestiLigas App");
        iniciarComponentes();
        configurarComportamientos(); 
    }

    private void iniciarComponentes() {
        content = new VerticalLayout();
        setContent(content);
        content.setHeight(100, Unit.PERCENTAGE);
        content.setWidth(100, Unit.PERCENTAGE);
        content.setSpacing(true);
        content.setMargin(true);
        
        row1 = new GridLayout(3, 1);
        row1.setWidth("100%");
        row1.setHeight("100%");
        bienven = new Label("!Bienvenidos!\nGestiLigas App", ContentMode.PREFORMATTED);
        bienven.setStyleName("bienvenPrinc", true);
        row1.addComponent(bienven, 1, 0);
        
        row2 = new GridLayout(7, 1);
        row2.setSizeFull();
        row2.setSpacing(true);
        login = new Button("Login");
        login.setImmediate(true);
        row2.addComponent(login, 4, 0);
        row2.setColumnExpandRatio(0, 1);
        row2.setColumnExpandRatio(1, 1);
        row2.setColumnExpandRatio(2, 1);
        row2.setColumnExpandRatio(6, 1);
        registro = new Button("Registro");
        registro.setImmediate(true);
        row2.addComponent(registro, 5, 0);
        
        content.addComponent(row1);
        content.addComponent(row2);
        content.setExpandRatio(row1, 2);
        content.setExpandRatio(row2, 1);
    }

    private void configurarComportamientos() {
        login.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getPage().setLocation("/Login");
            }
        });
        
        registro.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getPage().setLocation("/Registro");
            }
        });
    }

    @WebServlet(urlPatterns = "/*", name = "PrincipalServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Principal.class, productionMode = false)
    public static class PrincipalServlet extends VaadinServlet {
    }
}
