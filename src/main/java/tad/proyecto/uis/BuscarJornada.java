package tad.proyecto.uis;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.Property;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WrappedSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import tad.proyecto.dao.ClasificacionDao;
import tad.proyecto.dao.ClasificacionDaoImpl;
import tad.proyecto.dao.JornadasDao;
import tad.proyecto.dao.JornadasDaoImpl;
import tad.proyecto.entidades.Jornada;
import tad.proyecto.entidades.Usuario;
import org.vaadin.dialogs.ConfirmDialog;

/**
 * @author PedroMadrigal
 * @version 1.0
 * @since 2016
 */
@Theme("mytheme")
@Widgetset("tad.proyecto.madrigalgutierrezpedroantonio.MyAppWidgetset")
public class BuscarJornada extends UI{
    
    private Panel panel;
    private VerticalLayout content;
    private GridLayout row1;
    private Label bienven;
    private GridLayout row2;
    private GridLayout row21;
    private GridLayout row22;
    private Usuario usuario;
    private ComboBox selectJornada;
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
    private List<Integer> jornadasNumeros;
    private JornadasDao jornadasDao = new JornadasDaoImpl();
    private ClasificacionDao clasificacionDao = new ClasificacionDaoImpl();
    private Button goActualizar;
    private Button goBorrar;
    private Button aceptar;
    private Button cancelar;
    
    public BuscarJornada(){
        
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
        content.setSizeFull();
        content.setWidth("100%");
        content.setHeight("100%");
        panel = new Panel(content);
        setContent(panel);
        content.setMargin(true);
        content.setSpacing(true);
        content.setImmediate(true);
        
        row1 = new GridLayout(3, 1);
        row1.setWidth("100%");
        row1.setHeight("100%");
        bienven = new Label("Bienvenido " + usuario.getNombre(), ContentMode.PREFORMATTED);
        bienven.setStyleName("bienvenPrinc", true);
        row1.addComponent(bienven, 1, 0);
        
        row2 = new GridLayout(6, 15);
        row2.setSpacing(true);
        row2.setMargin(true);
        row2.setWidth("100%");
        row2.setHeight("100%");
        
        try {
            jornadasNumeros = jornadasDao.getJornadasTerminadas();
        } catch (final Exception e) {
            e.printStackTrace();
        }
       
        selectJornada = new ComboBox("Seleccione una jornada para visualizar");
        selectJornada.setStyleName("caption1", true);
        selectJornada.addItems(jornadasNumeros);
        row21 = new GridLayout(4, 4);
        row21.addComponent(selectJornada, 3, 0);
        row21.setMargin(true);
        row21.setSpacing(true);
        
        goInicio = new Button("Ir Inicio");
        row21.addComponent(goInicio, 3, 2);
        row21.addComponent(new Label(), 0, 0);
        row2.addComponent(row21, 2, 0);
        row2.setRowExpandRatio(0, 1);
        row2.addComponent(new Label(), 0, 1);
        row2.setColumnExpandRatio(0, 1);
        row2.setColumnExpandRatio(1, 1);
        row2.addComponent(new Label(), 0, 14);
        row2.setRowExpandRatio(14, 1);
        row21.setColumnExpandRatio(0, 1);
        row21.setColumnExpandRatio(1, 1);
        
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
        
        selectJornada.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                
                if(selectJornada.getValue() == null){
                    Notification.show("Debe seleccionar una jornada correctamente.", Notification.Type.WARNING_MESSAGE);
                }else{
                
                    row22 = new GridLayout(3, 13);
                    row22.setImmediate(true);
                    row22.setSpacing(true);
                    row22.setMargin(true);
                    row2.removeComponent(3, 0);
                    row22.removeAllComponents();

                    cancelarEdicion();

                    goActualizar.addClickListener(new Button.ClickListener() {
                        @Override
                        public void buttonClick(Button.ClickEvent event) {
                            actualizarJornada();
                        }

                    });

                    goBorrar.addClickListener(new Button.ClickListener() {
                        @Override
                        public void buttonClick(Button.ClickEvent event) {
                            borrarJornada();
                        }
                    });
                }
            }
        });
    }
    
    /**
     * Este método se utiliza para actualizar una jornada seleccionada.
     * @return No devuelve nada
     * @exception Exception
     * @see Exception
     */
    private void actualizarJornada() {
        
        final List<Jornada> jornadaAntes = new ArrayList<Jornada>();   
        jornadaAntes.addAll(jornadas);
        
        labEquipo1.setReadOnly(false);
        labEquipo2.setReadOnly(false);
        labEquipo3.setReadOnly(false);
        labEquipo4.setReadOnly(false);
        labEquipo5.setReadOnly(false);
        labEquipo6.setReadOnly(false);
        labEquipo7.setReadOnly(false);
        labEquipo8.setReadOnly(false);
        labEquipo9.setReadOnly(false);
        labEquipo10.setReadOnly(false);
        labEquipo11.setReadOnly(false);
        labEquipo12.setReadOnly(false);
        labEquipo13.setReadOnly(false);
        labEquipo14.setReadOnly(false);
        labEquipo15.setReadOnly(false);
        labEquipo16.setReadOnly(false);
        labEquipo17.setReadOnly(false);
        labEquipo18.setReadOnly(false);
        labEquipo19.setReadOnly(false);
        labEquipo20.setReadOnly(false);
        row2.removeComponent(5, 0);
        row2.removeComponent(4, 0);
        aceptar = new Button("Aceptar");
        row2.addComponent(aceptar, 4, 0);
        cancelar = new Button("Cancelar");
        row2.addComponent(cancelar, 5, 0);
        
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
                }
                
                try {
                    clasificacionDao.calculaClasificacion2(jornadaAntes, jornadas);
                } catch (final Exception e) {
                    e.printStackTrace();
                    Notification.show("Ocurrió un error", Notification.Type.ERROR_MESSAGE);
                }
                
                getPage().setLocation("/Inicio");
            }
        });
        
        cancelar.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                cancelarEdicion();
            }
        });
    }
    
    /**
     * Este método se utiliza para cancelar la edición de una jornada.
     * @return No devuelve nada
     * @exception Exception
     * @see Exception
     */
    private void cancelarEdicion() {
        
        if(selectJornada.getValue() == null){
            Notification.show("Debe seleccionar una jornada correctamente", Notification.Type.WARNING_MESSAGE);
        }else{
            try {
                jornadas = jornadasDao.getJornada((Integer)selectJornada.getValue());
            } catch(final Exception e){
                e.printStackTrace();
                Notification.show("Ha ocurrido un problema con la jornada seleccionada.", Notification.Type.ERROR_MESSAGE);
            }

            labEquipo1 = new TextField(jornadas.get(0).getNombreEquipoLocal());
            labEquipo1.setStyleName("caption1", true);
            labEquipo1.setValue(jornadas.get(0).getGolesEquipoLocal() + "");
            labEquipo1.setReadOnly(true);
            labEquipo2 = new TextField(jornadas.get(0).getNombreEquipoVisitante());
            labEquipo2.setStyleName("caption1", true);
            labEquipo2.setValue(jornadas.get(0).getGolesEquipoVisitante() + "");
            labEquipo2.setReadOnly(true);
            labEquipo3 = new TextField(jornadas.get(1).getNombreEquipoLocal());
            labEquipo3.setStyleName("caption1", true);
            labEquipo3.setValue(jornadas.get(1).getGolesEquipoLocal() + "");
            labEquipo3.setReadOnly(true);
            labEquipo4 = new TextField(jornadas.get(1).getNombreEquipoVisitante());
            labEquipo4.setStyleName("caption1", true);
            labEquipo4.setValue(jornadas.get(1).getGolesEquipoVisitante() + "");
            labEquipo4.setReadOnly(true);
            labEquipo5 = new TextField(jornadas.get(2).getNombreEquipoLocal());
            labEquipo5.setStyleName("caption1", true);
            labEquipo5.setValue(jornadas.get(2).getGolesEquipoLocal() + "");
            labEquipo5.setReadOnly(true);
            labEquipo6 = new TextField(jornadas.get(2).getNombreEquipoVisitante());
            labEquipo6.setStyleName("caption1", true);
            labEquipo6.setValue(jornadas.get(2).getGolesEquipoVisitante() + "");
            labEquipo6.setReadOnly(true);
            labEquipo7 = new TextField(jornadas.get(3).getNombreEquipoLocal());
            labEquipo7.setStyleName("caption1", true);
            labEquipo7.setValue(jornadas.get(3).getGolesEquipoLocal() + "");
            labEquipo7.setReadOnly(true);
            labEquipo8 = new TextField(jornadas.get(3).getNombreEquipoVisitante());
            labEquipo8.setStyleName("caption1", true);
            labEquipo8.setValue(jornadas.get(3).getGolesEquipoVisitante() + "");
            labEquipo8.setReadOnly(true);
            labEquipo9 = new TextField(jornadas.get(4).getNombreEquipoLocal());
            labEquipo9.setStyleName("caption1", true);
            labEquipo9.setValue(jornadas.get(4).getGolesEquipoLocal() + "");
            labEquipo9.setReadOnly(true);
            labEquipo10 = new TextField(jornadas.get(4).getNombreEquipoVisitante());
            labEquipo10.setStyleName("caption1", true);
            labEquipo10.setValue(jornadas.get(4).getGolesEquipoVisitante() + "");
            labEquipo10.setReadOnly(true);
            labEquipo11 = new TextField(jornadas.get(5).getNombreEquipoLocal());
            labEquipo11.setStyleName("caption1", true);
            labEquipo11.setValue(jornadas.get(5).getGolesEquipoLocal() + "");
            labEquipo11.setReadOnly(true);
            labEquipo12 = new TextField(jornadas.get(5).getNombreEquipoVisitante());
            labEquipo12.setStyleName("caption1", true);
            labEquipo12.setValue(jornadas.get(5).getGolesEquipoVisitante() + "");
            labEquipo12.setReadOnly(true);
            labEquipo13 = new TextField(jornadas.get(6).getNombreEquipoLocal());
            labEquipo13.setStyleName("caption1", true);
            labEquipo13.setValue(jornadas.get(6).getGolesEquipoLocal() + "");
            labEquipo13.setReadOnly(true);
            labEquipo14 = new TextField(jornadas.get(6).getNombreEquipoVisitante());
            labEquipo14.setStyleName("caption1", true);
            labEquipo14.setValue(jornadas.get(6).getGolesEquipoVisitante() + "");
            labEquipo14.setReadOnly(true);
            labEquipo15 = new TextField(jornadas.get(7).getNombreEquipoLocal());
            labEquipo15.setStyleName("caption1", true);
            labEquipo15.setValue(jornadas.get(7).getGolesEquipoLocal() + "");
            labEquipo15.setReadOnly(true);
            labEquipo16 = new TextField(jornadas.get(7).getNombreEquipoVisitante());
            labEquipo16.setStyleName("caption1", true);
            labEquipo16.setValue(jornadas.get(7).getGolesEquipoVisitante() + "");
            labEquipo16.setReadOnly(true);
            labEquipo17 = new TextField(jornadas.get(8).getNombreEquipoLocal());
            labEquipo17.setStyleName("caption1", true);
            labEquipo17.setValue(jornadas.get(8).getGolesEquipoLocal() + "");
            labEquipo17.setReadOnly(true);
            labEquipo18 = new TextField(jornadas.get(8).getNombreEquipoVisitante());
            labEquipo18.setStyleName("caption1", true);
            labEquipo18.setValue(jornadas.get(8).getGolesEquipoVisitante() + "");
            labEquipo18.setReadOnly(true);
            labEquipo19 = new TextField(jornadas.get(9).getNombreEquipoLocal());
            labEquipo19.setStyleName("caption1", true);
            labEquipo19.setValue(jornadas.get(9).getGolesEquipoLocal() + "");
            labEquipo19.setReadOnly(true);
            labEquipo20 = new TextField(jornadas.get(9).getNombreEquipoVisitante());
            labEquipo20.setStyleName("caption1", true);
            labEquipo20.setValue(jornadas.get(9).getGolesEquipoVisitante() + "");
            labEquipo20.setReadOnly(true);

            row22.removeComponent(1, 0);
            row22.removeComponent(2, 0);
            row22.removeComponent(1, 1);
            row22.removeComponent(2, 1);
            row22.removeComponent(1, 2);
            row22.removeComponent(2, 2);
            row22.removeComponent(1, 3);
            row22.removeComponent(2, 3);
            row22.removeComponent(1, 4);
            row22.removeComponent(2, 4);
            row22.removeComponent(1, 6);
            row22.removeComponent(2, 6);
            row22.removeComponent(1, 7);
            row22.removeComponent(2, 7);
            row22.removeComponent(1, 8);
            row22.removeComponent(2, 8);
            row22.removeComponent(1, 9);
            row22.removeComponent(2, 9);
            row22.removeComponent(1, 10);
            row22.removeComponent(2, 10);

            row22.addComponent(labEquipo1, 1, 0);
            row22.addComponent(labEquipo2, 2, 0);
            row22.addComponent(labEquipo3, 1, 1);
            row22.addComponent(labEquipo4, 2, 1);
            row22.addComponent(labEquipo5, 1, 2);
            row22.addComponent(labEquipo6, 2, 2);
            row22.addComponent(labEquipo7, 1, 3);
            row22.addComponent(labEquipo8, 2, 3);
            row22.addComponent(labEquipo9, 1, 4);
            row22.addComponent(labEquipo10, 2, 4);
            row22.addComponent(labEquipo11, 1, 6);
            row22.addComponent(labEquipo12, 2, 6);
            row22.addComponent(labEquipo13, 1, 7);
            row22.addComponent(labEquipo14, 2, 7);
            row22.addComponent(labEquipo15, 1, 8);
            row22.addComponent(labEquipo16, 2, 8);
            row22.addComponent(labEquipo17, 1, 9);
            row22.addComponent(labEquipo18, 2, 9);
            row22.addComponent(labEquipo19, 1, 10);
            row22.addComponent(labEquipo20, 2, 10);

            row2.removeComponent(3, 0);
            row2.addComponent(row22, 3, 0);

            goActualizar = new Button("Actualizar jornada");
            goBorrar = new Button("Eliminar jornada");

            row2.removeComponent(4, 0);
            row2.removeComponent(5, 0);
            row2.addComponent(goActualizar, 4, 0);
            row2.addComponent(goBorrar, 5, 0);

            goActualizar.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    actualizarJornada();
                }
            });

            goBorrar.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    borrarJornada();
                }
            });
        }
    }
    
    /**
     * Este método se utiliza para borrar una jornada seleccionada.
     * @return No devuelve nada
     * @exception Exception
     * @see Exception
     */
    private void borrarJornada() {
        final List<Jornada> jornadaAntes = new ArrayList<Jornada>();   
        jornadaAntes.addAll(jornadas);
        
        ConfirmDialog d = ConfirmDialog.show(getUI(), "Eliminar jornada", "¿Está seguro que desea eliminar la jornada seleccionada?", "Sí", "No", new ConfirmDialog.Listener() {
            @Override
            public void onClose(final ConfirmDialog cd) {
                 
                if(cd.isConfirmed()){         
                    try {
                        jornadasDao.borrarJornadas(jornadaAntes);
                    } catch (final Exception e){
                        e.printStackTrace();
                    }
                    
                    try {
                        clasificacionDao.calculaClasificacion3(jornadaAntes);
                    } catch (final Exception e){
                        e.printStackTrace();
                    }
                    
                    cd.close(); 
                }
                 
                if(cd.isCanceled()){
                    cd.close();
                }
            }
        });
        d.setStyleName(Reindeer.WINDOW_BLACK);
        d.setContentMode(ConfirmDialog.ContentMode.HTML);
        d.setHeight("16em");
        d.getCancelButton().setStyleName(Reindeer.BUTTON_LINK);
    }

    @WebServlet(urlPatterns = "/BuscarJornada/*", name = "BuscarJornadaServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = BuscarJornada.class, productionMode = false)
    public static class BuscarJornadaServlet extends VaadinServlet {
    }
}
