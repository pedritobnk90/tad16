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
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
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
public class Inicio extends UI{
    
    private Panel panel;
    private VerticalLayout content;
    private GridLayout row1;
    private Label bienven;
    private GridLayout row2;
    private GridLayout row21;
    private Usuario usuario;
    private Table clasificacionTabla;
    private Button goAnadirJornada;
    private Button goBuscarJornada;
    
    public Inicio(){
        
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
        
        row2 = new GridLayout(7, 1);
        row2.setSpacing(true);
        row21 = new GridLayout(2, 4);
        row21.setSpacing(true);
        
        String fechaHoy = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        clasificacionTabla = new Table("Clasificación a día " + fechaHoy);
        clasificacionTabla.addContainerProperty("Equipo", String.class, null);
        clasificacionTabla.addContainerProperty("Posición", Integer.class, null);
        clasificacionTabla.addContainerProperty("Puntos", Integer.class, null);
        clasificacionTabla.addContainerProperty("Goles a Favor", Integer.class, null);
        clasificacionTabla.addContainerProperty("Goles en Contra", Integer.class, null);
        clasificacionTabla.addContainerProperty("Diferencia de Goles", Integer.class, null);
        
        try{
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tad16", "root", "");
            Statement s = conexion.createStatement(); 
            ResultSet rs = s.executeQuery ("SELECT e.nombre, c.posicion, c.puntos, c.golesFavor,"
                + " c.golesContra, c.difGoles FROM Clasificacion c LEFT JOIN Equipo e ON (e.id = c.idEquipo) "
                + "LEFT JOIN Liga l ON (l.idCategoria = e.liga AND l.idCategoria = c.idLiga) WHERE "
                + "l.idCategoria = (SELECT liga FROM Liga li LEFT JOIN Equipo eq ON (eq.liga = li.idCategoria) "
                + "WHERE eq.id = " + usuario.getEquipoFavorito() + ") ORDER BY c.posicion ASC");
            int i = 1;
            while(rs.next()){
                clasificacionTabla.addItem(new Object[]{rs.getString(1), rs.getInt(2), 
                rs.getInt(3), rs.getInt(4), rs.getInt(5), 
                rs.getInt(6)}, i);
                i++;
            }
        } catch(final Exception e){
            e.printStackTrace();
        }
        clasificacionTabla.setPageLength(clasificacionTabla.size());
        clasificacionTabla.setStyleName("caption1");
        
        row2.addComponent(clasificacionTabla, 4, 0);
        
        goAnadirJornada = new Button("Añadir jornada");
        goBuscarJornada = new Button("Buscar --> Actualizar/Borrar jornada");
        row21.addComponent(goAnadirJornada, 0, 2);
        row21.addComponent(goBuscarJornada, 1, 2);
        
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
        goAnadirJornada.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getPage().setLocation("/AnadirJornada");
            }
        });
        goBuscarJornada.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getPage().setLocation("/BuscarJornada");
            }
        });
    }

    @WebServlet(urlPatterns = "/Inicio/*", name = "InicioServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Inicio.class, productionMode = false)
    public static class InicioServlet extends VaadinServlet {
    }
}
