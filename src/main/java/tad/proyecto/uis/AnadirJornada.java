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
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import tad.proyecto.dao.ClasificacionDao;
import tad.proyecto.dao.ClasificacionDaoImpl;
import tad.proyecto.dao.JornadasDao;
import tad.proyecto.dao.JornadasDaoImpl;
import tad.proyecto.entidades.Jornada;
import tad.proyecto.entidades.Usuario;

/**
 * @author PedroMadrigal
 * @version 1.0
 * @since 2016
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
    private List<Jornada> jornadas;
    private final JornadasDao jornadasDao = new JornadasDaoImpl();
    private final ClasificacionDao clasificacionDao = new ClasificacionDaoImpl();
    private int siguienteJornada;
    private Button aceptar;
    
    public AnadirJornada(){
        
    }
    
    /**
     * Este método inicializa el ui. Siempre es ejecutado al abrir un Ui.
     * @param vaadinRequest Único parámetro del método.
     * @see Exception
     */
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

    /**
     * Este método se utiliza para inicializar todos los componentes de Vaadin.
     * @return No devuelve nada
     * @exception Exception
     * @see Exception
     */
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
        
        row2 = new GridLayout(7, 13);
        row2.setSpacing(true);
        row21 = new GridLayout(2, 4);
        row21.setSpacing(true);
        
        try {
            siguienteJornada = jornadasDao.getSiguienteJornada();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        
        try {
            jornadas = jornadasDao.getJornada(siguienteJornada);
        } catch (final Exception e) {
            e.printStackTrace();
        }
       
        labEquipo1 = new TextField(jornadas.get(0).getNombreEquipoLocal());
        labEquipo1.setRequired(true);
        labEquipo1.setStyleName("caption1", true);
        labEquipo2 = new TextField(jornadas.get(0).getNombreEquipoVisitante());
        labEquipo2.setRequired(true);
        labEquipo2.setStyleName("caption1", true);
        labEquipo3 = new TextField(jornadas.get(1).getNombreEquipoLocal());
        labEquipo3.setRequired(true);
        labEquipo3.setStyleName("caption1", true);
        labEquipo4 = new TextField(jornadas.get(1).getNombreEquipoVisitante());
        labEquipo4.setRequired(true);
        labEquipo4.setStyleName("caption1", true);
        labEquipo5 = new TextField(jornadas.get(2).getNombreEquipoLocal());
        labEquipo5.setRequired(true);
        labEquipo5.setStyleName("caption1", true);
        labEquipo6 = new TextField(jornadas.get(2).getNombreEquipoVisitante());
        labEquipo6.setRequired(true);
        labEquipo6.setStyleName("caption1", true);
        labEquipo7 = new TextField(jornadas.get(3).getNombreEquipoLocal());
        labEquipo7.setRequired(true);
        labEquipo7.setStyleName("caption1", true);
        labEquipo8 = new TextField(jornadas.get(3).getNombreEquipoVisitante());
        labEquipo8.setRequired(true);
        labEquipo8.setStyleName("caption1", true);
        labEquipo9 = new TextField(jornadas.get(4).getNombreEquipoLocal());
        labEquipo9.setRequired(true);
        labEquipo9.setStyleName("caption1", true);
        labEquipo10 = new TextField(jornadas.get(4).getNombreEquipoVisitante());
        labEquipo10.setRequired(true);
        labEquipo10.setStyleName("caption1", true);
        labEquipo11 = new TextField(jornadas.get(5).getNombreEquipoLocal());
        labEquipo11.setRequired(true);
        labEquipo11.setStyleName("caption1", true);
        labEquipo12 = new TextField(jornadas.get(5).getNombreEquipoVisitante());
        labEquipo12.setRequired(true);
        labEquipo12.setStyleName("caption1", true);
        labEquipo13 = new TextField(jornadas.get(6).getNombreEquipoLocal());
        labEquipo13.setRequired(true);
        labEquipo13.setStyleName("caption1", true);
        labEquipo14 = new TextField(jornadas.get(6).getNombreEquipoVisitante());
        labEquipo14.setRequired(true);
        labEquipo14.setStyleName("caption1", true);
        labEquipo15 = new TextField(jornadas.get(7).getNombreEquipoLocal());
        labEquipo15.setRequired(true);
        labEquipo15.setStyleName("caption1", true);
        labEquipo16 = new TextField(jornadas.get(7).getNombreEquipoVisitante());
        labEquipo16.setRequired(true);
        labEquipo16.setStyleName("caption1", true);
        labEquipo17 = new TextField(jornadas.get(8).getNombreEquipoLocal());
        labEquipo17.setRequired(true);
        labEquipo17.setStyleName("caption1", true);
        labEquipo18 = new TextField(jornadas.get(8).getNombreEquipoVisitante());
        labEquipo18.setRequired(true);
        labEquipo18.setStyleName("caption1", true);
        labEquipo19 = new TextField(jornadas.get(9).getNombreEquipoLocal());
        labEquipo19.setRequired(true);
        labEquipo19.setStyleName("caption1", true);
        labEquipo20 = new TextField(jornadas.get(9).getNombreEquipoVisitante());
        labEquipo20.setRequired(true);
        labEquipo20.setStyleName("caption1", true);
           
        aceptar = new Button("Continuar");
        
        row2.addComponent(labEquipo1, 4, 0);
        row2.addComponent(labEquipo2, 5, 0);
        row2.addComponent(labEquipo3, 4, 1);
        row2.addComponent(labEquipo4, 5, 1);
        row2.addComponent(labEquipo5, 4, 2);
        row2.addComponent(labEquipo6, 5, 2);
        row2.addComponent(labEquipo7, 4, 3);
        row2.addComponent(labEquipo8, 5, 3);
        row2.addComponent(labEquipo9, 4, 4);
        row2.addComponent(labEquipo10, 5, 4);
        row2.addComponent(labEquipo11, 4, 6);
        row2.addComponent(labEquipo12, 5, 6);
        row2.addComponent(labEquipo13, 4, 7);
        row2.addComponent(labEquipo14, 5, 7);
        row2.addComponent(labEquipo15, 4, 8);
        row2.addComponent(labEquipo16, 5, 8);
        row2.addComponent(labEquipo17, 4, 9);
        row2.addComponent(labEquipo18, 5, 9);
        row2.addComponent(labEquipo19, 4, 10);
        row2.addComponent(labEquipo20, 5, 10);
        row2.addComponent(aceptar, 4, 11);
        
        goInicio = new Button("Ir Inicio");
        row2.addComponent(goInicio, 5, 11);
        
        row2.setColumnExpandRatio(0, 1);
        row2.setColumnExpandRatio(1, 1);
        row2.setColumnExpandRatio(2, 1);
        
        content.addComponent(row1);
        content.addComponent(row2);
        content.setExpandRatio(row1, 1);
        content.setExpandRatio(row2, 1);
    }

    /**
     * Este método se utiliza para definir los comportamientos para cada componente de Vaadin.
     * @return No devuelve nada
     * @exception Exception
     * @see Exception
     */
    private void configurarComportamientos() {
        goInicio.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getPage().setLocation("/Inicio");
            }
        });
        
        aceptar.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                jornadas.get(0).setGolesEquipoLocal(Integer.valueOf(labEquipo1.getValue()));
                jornadas.get(0).setGolesEquipoVisitante(Integer.valueOf(labEquipo2.getValue()));
                jornadas.get(1).setGolesEquipoLocal(Integer.valueOf(labEquipo3.getValue()));
                jornadas.get(1).setGolesEquipoVisitante(Integer.valueOf(labEquipo4.getValue()));
                jornadas.get(2).setGolesEquipoLocal(Integer.valueOf(labEquipo5.getValue()));
                jornadas.get(2).setGolesEquipoVisitante(Integer.valueOf(labEquipo6.getValue()));
                jornadas.get(3).setGolesEquipoLocal(Integer.valueOf(labEquipo7.getValue()));
                jornadas.get(3).setGolesEquipoVisitante(Integer.valueOf(labEquipo8.getValue()));
                jornadas.get(4).setGolesEquipoLocal(Integer.valueOf(labEquipo9.getValue()));
                jornadas.get(4).setGolesEquipoVisitante(Integer.valueOf(labEquipo10.getValue()));
                jornadas.get(5).setGolesEquipoLocal(Integer.valueOf(labEquipo11.getValue()));
                jornadas.get(5).setGolesEquipoVisitante(Integer.valueOf(labEquipo12.getValue()));
                jornadas.get(6).setGolesEquipoLocal(Integer.valueOf(labEquipo13.getValue()));
                jornadas.get(6).setGolesEquipoVisitante(Integer.valueOf(labEquipo14.getValue()));
                jornadas.get(7).setGolesEquipoLocal(Integer.valueOf(labEquipo15.getValue()));
                jornadas.get(7).setGolesEquipoVisitante(Integer.valueOf(labEquipo16.getValue()));
                jornadas.get(8).setGolesEquipoLocal(Integer.valueOf(labEquipo17.getValue()));
                jornadas.get(8).setGolesEquipoVisitante(Integer.valueOf(labEquipo18.getValue()));
                jornadas.get(9).setGolesEquipoLocal(Integer.valueOf(labEquipo19.getValue()));
                jornadas.get(9).setGolesEquipoVisitante(Integer.valueOf(labEquipo20.getValue()));
                
                try {
                    jornadasDao.updateJornadas(jornadas);
                } catch (final Exception e) {
                    e.printStackTrace();
                    Notification.show("Ocurrió un error", Notification.Type.ERROR_MESSAGE);
                }
                
                try {
                    clasificacionDao.calculaClasificacion(jornadas);
                } catch (final Exception e) {
                    e.printStackTrace();
                    Notification.show("Ocurrió un error", Notification.Type.ERROR_MESSAGE);
                }
                
                getPage().setLocation("/Inicio");
            }
        });
    }

    @WebServlet(urlPatterns = "/AnadirJornada/*", name = "AnadirJornadaServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = AnadirJornada.class, productionMode = false)
    public static class AnadirJornadaServlet extends VaadinServlet {
    }
}
