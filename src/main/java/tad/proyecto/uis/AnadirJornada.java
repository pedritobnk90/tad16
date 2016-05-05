package tad.proyecto.uis;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WrappedSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import tad.proyecto.entidades.Equipo;
import tad.proyecto.entidades.Usuario;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("tad.proyecto.madrigalgutierrezpedroantonio.MyAppWidgetset")
public class AnadirJornada extends UI{
    
    private Panel panel;
    private VerticalLayout content;
    private GridLayout row1;
    private Label bienven;
    private GridLayout row2;
    private GridLayout row21;
    private Usuario usuario;
    private Button goInicio;
    private TextField labEquipo1;
    private TextField labEquipo2;
    private TextField labEquipo3;
    private TextField labEquipo4;
    private TextField labEquipo5;
    private TextField labEquipo6;
    private TextField labEquipo7;
    private TextField labEquipo8;
    private TextField labEquipo9;
    private TextField labEquipo10;
    private TextField labEquipo11;
    private TextField labEquipo12;
    private TextField labEquipo13;
    private TextField labEquipo14;
    private TextField labEquipo15;
    private TextField labEquipo16;
    private TextField labEquipo17;
    private TextField labEquipo18;
    private TextField labEquipo19;
    private TextField labEquipo20;
    private List<Equipo> equipos;
    
    public AnadirJornada(){
        
    }
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        getPage().setTitle("GestiLigas App");
        WrappedSession session = getSession().getSession();
        usuario = (Usuario) session.getAttribute("usuario");
        if(usuario == null){
            getPage().setLocation("/");
        }else{
            iniciarComponentes();
            configurarComportamientos(); 
        }
    }

    private void iniciarComponentes() {
        content = new VerticalLayout();
        panel = new Panel(content);
        setContent(panel);
        content.setSizeFull();
        content.setMargin(true);
        content.setSpacing(true);
        
        row1 = new GridLayout(3, 1);
        row1.setWidth("100%");
        row1.setHeight("100%");
        bienven = new Label("Bienvenido " + usuario.getNombre(), ContentMode.PREFORMATTED);
        bienven.setStyleName("bienvenPrinc", true);
        row1.addComponent(bienven, 1, 0);
        
        row2 = new GridLayout(7, 11);
        row2.setSpacing(true);
        row21 = new GridLayout(2, 4);
        row21.setSpacing(true);
        
        equipos = new ArrayList<Equipo>();
       
        labEquipo1 = new TextField();
        
        
//        row2.addComponent(clasificacionTabla, 4, 0);
        
        goInicio = new Button("Ir Inicio");
        row21.addComponent(goInicio, 0, 2);
        
        row2.setColumnExpandRatio(0, 1);
        row2.setColumnExpandRatio(1, 1);
        row2.setColumnExpandRatio(2, 1);
        
        row2.addComponent(row21, 5, 0);
        
        content.addComponent(row1);
        content.addComponent(row2);
        content.setExpandRatio(row1, 1);
        content.setExpandRatio(row2, 1);
    }

    private void configurarComportamientos() {
        goInicio.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getPage().setLocation("/Inicio");
            }
        });
    }

    @WebServlet(urlPatterns = "/AnadirJornada/*", name = "AnadirJornadaServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = AnadirJornada.class, productionMode = false)
    public static class AnadirJornadaServlet extends VaadinServlet {
    }
}
