package tad.proyecto.uis;

import javax.servlet.annotation.WebServlet;

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
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
public class Login extends UI {
    
    private VerticalLayout content;
    private GridLayout row1;
    private Label loginLab;
    private GridLayout row2;
    private Button continuar;
    private Button cancelar;
    private TextField username;
    private PasswordField pass;
    private Usuario usuario;
    
    public Login(){
        
    }
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        WrappedSession session = getSession().getSession();
        usuario = (Usuario) session.getAttribute("usuario");
        if(usuario != null){
            getPage().setLocation("/Inicio");
        }else{
            iniciarComponentes();
            setContent(content);
            configurarComportamientos();
            getPage().setTitle("Gestión de ligas");
        }
    }

    private void iniciarComponentes() {
        content = new VerticalLayout();
        setContent(content);
        content.setHeight(100, Unit.PERCENTAGE);
        content.setWidth(100, Unit.PERCENTAGE);
        content.setSpacing(true);
        content.setMargin(true);
        
        row1 = new GridLayout(8, 3);
        row1.setSpacing(true);
        row1.setWidth("100%");
        row1.setHeight("100%");
        loginLab = new Label("Introduzca sus datos:", ContentMode.PREFORMATTED);
        loginLab.setStyleName("loginLab", true);
        row1.addComponent(loginLab, 4, 0);
        username = new TextField("Introduzca su nombre de usuario");
        username.setStyleName("caption1");
        row1.addComponent(username, 5, 1);
        pass = new PasswordField("Introduzca su contraseña");
        pass.setStyleName("caption1");
        row1.addComponent(pass, 5, 2);
        row1.setColumnExpandRatio(0, 1);
        row1.setColumnExpandRatio(1, 1);
        row1.setColumnExpandRatio(2, 1);
        row1.setColumnExpandRatio(3, 1);
        row1.setColumnExpandRatio(4, 1);
        row1.setColumnExpandRatio(6, 1);
        row1.setColumnExpandRatio(7, 1);
        
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
                    Notification.show("Debe introducir su nombre de usuario", Notification.Type.WARNING_MESSAGE);
                }else if(pass.getValue().trim().equals("")){
                    Notification.show("Debe introducir su contraseña", Notification.Type.WARNING_MESSAGE);
                }else{
                    try{
                        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tad16", "root", "");
                        Statement s = conexion.createStatement(); 
                        ResultSet rs = s.executeQuery ("SELECT * FROM Usuario WHERE username='" + username.getValue() + "'");
                        if(rs.next()){
                            usuario = new Usuario(rs.getInt("id"), rs.getString("username"), rs.getString("pass"), 
                                    rs.getString("nombre"), rs.getString("apellidos"), rs.getInt("equipoFavorito"));
                            conexion.close();
                            if(usuario.getPass().equals(pass.getValue())){
                                WrappedSession session = getSession().getSession(); 
                                session.setAttribute("usuario", usuario);
                                session.setMaxInactiveInterval(600);
                                getPage().setLocation("/Inicio");
                            }else{
                                Notification.show("Nombre de usuario o contraseña no válidos", Notification.Type.WARNING_MESSAGE);
                            }
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

    @WebServlet(urlPatterns = "/Login/*", name = "LoginServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Login.class, productionMode = false)
    public static class LoginServlet extends VaadinServlet {
    }
}
