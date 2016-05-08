package tad.proyecto.uis;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WrappedSession;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
public class Registro extends UI {
    
    private VerticalLayout content;
    private GridLayout row1;
    private Label loginLab;
    private GridLayout row2;
    private Button continuar;
    private Button cancelar;
    private TextField username;
    private PasswordField pass;
    private TextField nombre;
    private TextField apellidos;
    private ComboBox equiposDisponibles;
    private Usuario usuario;
    private List<Equipo> equipos;
    
    public Registro(){
        equipos = new ArrayList<Equipo>();
        try{
            final Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tad16", "root", "");
            final Statement s = conexion.createStatement(); 
            final ResultSet rs = s.executeQuery ("SELECT * FROM Equipo");
            while(rs.next()){
                equipos.add(new Equipo(rs.getInt("id"), rs.getString("nombre"), rs.getInt("liga")));
            }
        }catch(final Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        WrappedSession session = getSession().getSession();
        usuario = (Usuario) session.getAttribute("usuario");
        if(usuario != null){
            getPage().setLocation("/Inicio");
        }else{
            getEquipos();
            iniciarComponentes();
            setContent(content);
            configurarComportamientos();
            getPage().setTitle("Gestión de ligas");
        }
    }
    
    private void getEquipos() {
        
    }

    private void iniciarComponentes() {
        content = new VerticalLayout();
        setContent(content);
        content.setHeight(100, Unit.PERCENTAGE);
        content.setWidth(100, Unit.PERCENTAGE);
        content.setSpacing(true);
        content.setMargin(true);
        
        row1 = new GridLayout(8, 5);
        row1.setSpacing(true);
        row1.setWidth("100%");
        row1.setHeight("100%");
        loginLab = new Label("Introduzca sus datos:", ContentMode.PREFORMATTED);
        loginLab.setStyleName("loginLab", true);
        row1.addComponent(loginLab, 4, 0);
        username = new TextField("Nombre de usuario");
        username.setStyleName("caption1");
        row1.addComponent(username, 5, 1);
        pass = new PasswordField("Contraseña");
        pass.setStyleName("caption1");
        row1.addComponent(pass, 6, 1);
        row1.setColumnExpandRatio(0, 1);
        row1.setColumnExpandRatio(1, 1);
        row1.setColumnExpandRatio(2, 1);
        row1.setColumnExpandRatio(3, 1);
        row1.setColumnExpandRatio(4, 1);
        row1.setColumnExpandRatio(7, 1);
        nombre = new TextField("Nombre");
        nombre.setStyleName("caption1");
        row1.addComponent(nombre, 5, 2);
        apellidos = new TextField("Apellidos");
        apellidos.setStyleName("caption1");
        row1.addComponent(apellidos, 6, 2);
        equiposDisponibles = new ComboBox("Equipo favorito");
        equiposDisponibles.setFilteringMode(FilteringMode.CONTAINS);
        for(final Equipo equipo: equipos){
            equiposDisponibles.addItem(equipo.getNombre());
        }
        
        equiposDisponibles.setStyleName("caption1");
        row1.addComponent(equiposDisponibles, 5, 3);
        
        row2 = new GridLayout(7, 1);
        row2.setSizeFull();
        row2.setSpacing(true);
        continuar = new Button("Continuar");
        continuar.setImmediate(true);
        row2.addComponent(continuar, 4, 0);
        row2.setColumnExpandRatio(0, 1);
        row2.setColumnExpandRatio(1, 1);
        row2.setColumnExpandRatio(2, 1);
        row2.setColumnExpandRatio(6, 1);
        cancelar = new Button("Cancelar");
        cancelar.setImmediate(true);
        row2.addComponent(cancelar, 5, 0);
        
        content.addComponent(row1);
        content.addComponent(row2);
        content.setExpandRatio(row1, 2);
        content.setExpandRatio(row2, 1);
    }

    private void configurarComportamientos() {
        continuar.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if(username.getValue().trim().equals("")){
                    Notification.show("Debe introducir un nombre de usuario", Notification.Type.WARNING_MESSAGE);
                }else if(pass.getValue().trim().equals("")){
                    Notification.show("Debe introducir una contraseña", Notification.Type.WARNING_MESSAGE);
                }else if(nombre.getValue().trim().equals("")){
                    Notification.show("Debe introducir su nombre", Notification.Type.WARNING_MESSAGE);
                }else if(apellidos.getValue().trim().equals("")){
                    Notification.show("Debe introducir sus apellidos", Notification.Type.WARNING_MESSAGE);
                }else if(equiposDisponibles.getValue().equals("") || equiposDisponibles.getValue() == null){
                    Notification.show("Debe seleccionar su equipo favorito", Notification.Type.WARNING_MESSAGE);
                }else{
                    try{ 
                        Integer idEquipoFavorito = null;
                        for(final Equipo equipo : equipos){
                            if(equipo.getNombre().equals(equiposDisponibles.getValue())){
                                idEquipoFavorito = equipo.getId();
                            }
                        }
                        final Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/tad16", "root", "");
                        final Statement s = conexion.createStatement(); 
                        s.executeUpdate("INSERT INTO Usuario (username, pass, nombre, apellidos, equipoFavorito) "
                            + "VALUES ('" + username.getValue() + "','" + pass.getValue() + "','" + nombre.getValue() 
                            + "','" + apellidos.getValue() + "'," + idEquipoFavorito + ")");
                        final ResultSet rs2 = s.executeQuery ("SELECT * FROM Usuario WHERE username='" + username.getValue() + "'");
                        if(rs2.next()){
                            usuario = new Usuario(rs2.getInt("id"), rs2.getString("username"), rs2.getString("pass"), 
                                rs2.getString("nombre"), rs2.getString("apellidos"), rs2.getInt("equipoFavorito"));
                            conexion.close();
                            WrappedSession session = getSession().getSession(); 
                            session.setAttribute("usuario", usuario);
                            session.setMaxInactiveInterval(600);
                            getPage().setLocation("/Inicio");
                        }
                    }catch(final Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        
        cancelar.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getPage().setLocation("/");
            }
        });
    }

    @WebServlet(urlPatterns = "/Registro/*", name = "RegistroServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Registro.class, productionMode = false)
    public static class RegistroServlet extends VaadinServlet {
    }
}
