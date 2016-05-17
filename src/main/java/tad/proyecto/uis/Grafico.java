package tad.proyecto.uis;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
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
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import tad.proyecto.dao.ClasificacionDao;
import tad.proyecto.dao.ClasificacionDaoImpl;
import tad.proyecto.entidades.Clasificacion;
import tad.proyecto.entidades.Usuario;

/**
 * @author PedroMadrigal
 * @version 1.0
 * @since 2016
 */
@Theme("mytheme")
@Widgetset("tad.proyecto.madrigalgutierrezpedroantonio.MyAppWidgetset")
public class Grafico extends UI{
    
    private Panel panel;
    private VerticalLayout content;
    private GridLayout row1;
    private GridLayout row2;
    private Label bienven;
    private Usuario usuario;
    private ComboBox selectGrafico;
    private Chart grafico;
    private List<Clasificacion> clasificacion;
    private int minGolesFavor;
    private int maxGolesFavor;
    private int minGolesContra;
    private int maxGolesContra;
    private int minDifGoles;
    private int maxDifGoles;
    private ClasificacionDao clasificacionDao = new ClasificacionDaoImpl();
    
    public Grafico(){
        
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
        row1.setMargin(true);
        row1.setSpacing(true);
        
        bienven = new Label("Bienvenido " + usuario.getNombre(), ContentMode.PREFORMATTED);
        bienven.setStyleName("bienvenPrinc", true);
        row1.addComponent(bienven, 1, 0);
        
        row2 = new GridLayout(2, 4);
        row2.setWidth("100%");
        row2.setHeight("100%");
        row2.setMargin(true);
        row2.setSpacing(true);
        
        try {
            clasificacion = clasificacionDao.getClasificacionActualEquipos();
        } catch(final Exception e) {
            e.printStackTrace();
        }
        
        try {
            minGolesContra = clasificacionDao.getMinGolesContra();
            maxGolesContra = clasificacionDao.getMaxGolesContra();
            minGolesFavor = clasificacionDao.getMinGolesFavor();
            maxGolesFavor = clasificacionDao.getMaxGolesFavor();
            minDifGoles = clasificacionDao.getMinDifGoles();
            maxDifGoles = clasificacionDao.getMaxDifGoles();
        } catch(final Exception e) {
            e.printStackTrace();
        }
        
        selectGrafico = new ComboBox("Seleccione sobre qué desea ver el gráfico:");
        selectGrafico.setStyleName("caption1", true);
        selectGrafico.addItem("Goles a favor");
        selectGrafico.addItem("Goles en contra");
        selectGrafico.addItem("Diferencia de goles");
        selectGrafico.select("Goles a favor");
        row2.addComponent(selectGrafico, 0, 0);
        row2.setColumnExpandRatio(0, 0.5f);
        
        grafico = new Chart(ChartType.COLUMN);
        grafico.setStyleName("caption1", true);
        final Configuration conf = grafico.getConfiguration();
        conf.setTitle("Gráfico de los goles a favor por equipo");
        final XAxis x = new XAxis();
        x.setCategories(clasificacion.get(0).getNombreEquipo(), clasificacion.get(1).getNombreEquipo(), 
            clasificacion.get(2).getNombreEquipo(), clasificacion.get(3).getNombreEquipo(), 
            clasificacion.get(4).getNombreEquipo(), clasificacion.get(5).getNombreEquipo(), 
            clasificacion.get(6).getNombreEquipo(), clasificacion.get(7).getNombreEquipo(),
            clasificacion.get(8).getNombreEquipo(), clasificacion.get(9).getNombreEquipo(),
            clasificacion.get(10).getNombreEquipo(), clasificacion.get(11).getNombreEquipo(),
            clasificacion.get(12).getNombreEquipo(), clasificacion.get(13).getNombreEquipo(),
            clasificacion.get(14).getNombreEquipo(), clasificacion.get(15).getNombreEquipo(),
            clasificacion.get(16).getNombreEquipo(), clasificacion.get(17).getNombreEquipo(),
            clasificacion.get(18).getNombreEquipo(), clasificacion.get(19).getNombreEquipo());
        conf.addxAxis(x);
        
        final YAxis y = new YAxis();
        y.setMin(minGolesFavor);
        y.setMax(maxGolesFavor);
        y.setTitle("Número de goles a favor");
        conf.addyAxis(y);
        
        conf.addSeries(new ListSeries("Goles a favor", clasificacion.get(0).getGolesFavor(), 
            clasificacion.get(1).getGolesFavor(), clasificacion.get(2).getGolesFavor(),
            clasificacion.get(3).getGolesFavor(), 
            clasificacion.get(4).getGolesFavor(), clasificacion.get(5).getGolesFavor(), 
            clasificacion.get(6).getGolesFavor(), clasificacion.get(7).getGolesFavor(),
            clasificacion.get(8).getGolesFavor(), clasificacion.get(9).getGolesFavor(),
            clasificacion.get(10).getGolesFavor(), clasificacion.get(11).getGolesFavor(),
            clasificacion.get(12).getGolesFavor(), clasificacion.get(13).getGolesFavor(),
            clasificacion.get(14).getGolesFavor(), clasificacion.get(15).getGolesFavor(),
            clasificacion.get(16).getGolesFavor(), clasificacion.get(17).getGolesFavor(),
            clasificacion.get(18).getGolesFavor(), clasificacion.get(19).getGolesFavor()));
        
        grafico.drawChart(conf);
        
        row2.addComponent(grafico, 1, 1);
        row2.setColumnExpandRatio(1, 1.5f);
        
        content.addComponent(row1);
        content.addComponent(row2);
        content.setExpandRatio(row1, 1);
    }

    /**
     * Este método se utiliza para definir los comportamientos para cada componente de Vaadin.
     * @return No devuelve nada
     * @exception Exception
     * @see Exception
     */
    private void configurarComportamientos() {
        selectGrafico.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if(selectGrafico.getValue() != null){
                    row2.removeComponent(1, 1);
                    if(selectGrafico.getValue().equals("Goles a favor")){
                        grafico = new Chart(ChartType.COLUMN);
                        final Configuration conf = grafico.getConfiguration();
                        conf.setTitle("Gráfico de los goles a favor por equipo");
                        final XAxis x = new XAxis();
                        x.setCategories(clasificacion.get(0).getNombreEquipo(), clasificacion.get(1).getNombreEquipo(), 
                            clasificacion.get(2).getNombreEquipo(), clasificacion.get(3).getNombreEquipo(), 
                            clasificacion.get(4).getNombreEquipo(), clasificacion.get(5).getNombreEquipo(), 
                            clasificacion.get(6).getNombreEquipo(), clasificacion.get(7).getNombreEquipo(),
                            clasificacion.get(8).getNombreEquipo(), clasificacion.get(9).getNombreEquipo(),
                            clasificacion.get(10).getNombreEquipo(), clasificacion.get(11).getNombreEquipo(),
                            clasificacion.get(12).getNombreEquipo(), clasificacion.get(13).getNombreEquipo(),
                            clasificacion.get(14).getNombreEquipo(), clasificacion.get(15).getNombreEquipo(),
                            clasificacion.get(16).getNombreEquipo(), clasificacion.get(17).getNombreEquipo(),
                            clasificacion.get(18).getNombreEquipo(), clasificacion.get(19).getNombreEquipo());
                        conf.addxAxis(x);

                        final YAxis y = new YAxis();
                        y.setMin(minGolesFavor);
                        y.setMax(maxGolesFavor);
                        y.setTitle("Número de goles a favor");
                        conf.addyAxis(y);

                        conf.addSeries(new ListSeries("Goles a favor", clasificacion.get(0).getGolesFavor(), 
                            clasificacion.get(1).getGolesFavor(), clasificacion.get(2).getGolesFavor(),
                            clasificacion.get(3).getGolesFavor(), 
                            clasificacion.get(4).getGolesFavor(), clasificacion.get(5).getGolesFavor(), 
                            clasificacion.get(6).getGolesFavor(), clasificacion.get(7).getGolesFavor(),
                            clasificacion.get(8).getGolesFavor(), clasificacion.get(9).getGolesFavor(),
                            clasificacion.get(10).getGolesFavor(), clasificacion.get(11).getGolesFavor(),
                            clasificacion.get(12).getGolesFavor(), clasificacion.get(13).getGolesFavor(),
                            clasificacion.get(14).getGolesFavor(), clasificacion.get(15).getGolesFavor(),
                            clasificacion.get(16).getGolesFavor(), clasificacion.get(17).getGolesFavor(),
                            clasificacion.get(18).getGolesFavor(), clasificacion.get(19).getGolesFavor()));

                        grafico.drawChart(conf);

                        row2.addComponent(grafico, 1, 1);
                        row2.setColumnExpandRatio(1, 1.5f);
                    }else if(selectGrafico.getValue().equals("Goles en contra")){
                        grafico = new Chart(ChartType.COLUMN);
                        final Configuration conf = grafico.getConfiguration();
                        conf.setTitle("Gráfico de los goles en contra por equipo");
                        final XAxis x = new XAxis();
                        x.setCategories(clasificacion.get(0).getNombreEquipo(), clasificacion.get(1).getNombreEquipo(), 
                            clasificacion.get(2).getNombreEquipo(), clasificacion.get(3).getNombreEquipo(), 
                            clasificacion.get(4).getNombreEquipo(), clasificacion.get(5).getNombreEquipo(), 
                            clasificacion.get(6).getNombreEquipo(), clasificacion.get(7).getNombreEquipo(),
                            clasificacion.get(8).getNombreEquipo(), clasificacion.get(9).getNombreEquipo(),
                            clasificacion.get(10).getNombreEquipo(), clasificacion.get(11).getNombreEquipo(),
                            clasificacion.get(12).getNombreEquipo(), clasificacion.get(13).getNombreEquipo(),
                            clasificacion.get(14).getNombreEquipo(), clasificacion.get(15).getNombreEquipo(),
                            clasificacion.get(16).getNombreEquipo(), clasificacion.get(17).getNombreEquipo(),
                            clasificacion.get(18).getNombreEquipo(), clasificacion.get(19).getNombreEquipo());
                        conf.addxAxis(x);

                        final YAxis y = new YAxis();
                        y.setMin(minGolesContra);
                        y.setMax(maxGolesContra);
                        y.setTitle("Número de goles en contra");
                        conf.addyAxis(y);

                        conf.addSeries(new ListSeries("Goles en contra", clasificacion.get(0).getGolesContra(), 
                            clasificacion.get(1).getGolesContra(), clasificacion.get(2).getGolesContra(),
                            clasificacion.get(3).getGolesContra(), 
                            clasificacion.get(4).getGolesContra(), clasificacion.get(5).getGolesContra(), 
                            clasificacion.get(6).getGolesContra(), clasificacion.get(7).getGolesContra(),
                            clasificacion.get(8).getGolesContra(), clasificacion.get(9).getGolesContra(),
                            clasificacion.get(10).getGolesContra(), clasificacion.get(11).getGolesContra(),
                            clasificacion.get(12).getGolesContra(), clasificacion.get(13).getGolesContra(),
                            clasificacion.get(14).getGolesContra(), clasificacion.get(15).getGolesContra(),
                            clasificacion.get(16).getGolesContra(), clasificacion.get(17).getGolesContra(),
                            clasificacion.get(18).getGolesContra(), clasificacion.get(19).getGolesContra()));

                        grafico.drawChart(conf);

                        row2.addComponent(grafico, 1, 1);
                        row2.setColumnExpandRatio(1, 1.5f);
                    }else if(selectGrafico.getValue().equals("Diferencia de goles")){
                        grafico = new Chart(ChartType.COLUMN);
                        final Configuration conf = grafico.getConfiguration();
                        conf.setTitle("Gráfico de la diferencia de goles de cada equipo");
                        final XAxis x = new XAxis();
                        x.setCategories(clasificacion.get(0).getNombreEquipo(), clasificacion.get(1).getNombreEquipo(), 
                            clasificacion.get(2).getNombreEquipo(), clasificacion.get(3).getNombreEquipo(), 
                            clasificacion.get(4).getNombreEquipo(), clasificacion.get(5).getNombreEquipo(), 
                            clasificacion.get(6).getNombreEquipo(), clasificacion.get(7).getNombreEquipo(),
                            clasificacion.get(8).getNombreEquipo(), clasificacion.get(9).getNombreEquipo(),
                            clasificacion.get(10).getNombreEquipo(), clasificacion.get(11).getNombreEquipo(),
                            clasificacion.get(12).getNombreEquipo(), clasificacion.get(13).getNombreEquipo(),
                            clasificacion.get(14).getNombreEquipo(), clasificacion.get(15).getNombreEquipo(),
                            clasificacion.get(16).getNombreEquipo(), clasificacion.get(17).getNombreEquipo(),
                            clasificacion.get(18).getNombreEquipo(), clasificacion.get(19).getNombreEquipo());
                        conf.addxAxis(x);

                        final YAxis y = new YAxis();
                        y.setMin(minDifGoles);
                        y.setMax(maxDifGoles);
                        y.setTitle("Diferencia de goles");
                        conf.addyAxis(y);

                        conf.addSeries(new ListSeries("Diferencia de goles", clasificacion.get(0).getDifGoles(), 
                            clasificacion.get(1).getDifGoles(), clasificacion.get(2).getDifGoles(),
                            clasificacion.get(3).getDifGoles(), 
                            clasificacion.get(4).getDifGoles(), clasificacion.get(5).getDifGoles(), 
                            clasificacion.get(6).getDifGoles(), clasificacion.get(7).getDifGoles(),
                            clasificacion.get(8).getDifGoles(), clasificacion.get(9).getDifGoles(),
                            clasificacion.get(10).getDifGoles(), clasificacion.get(11).getDifGoles(),
                            clasificacion.get(12).getDifGoles(), clasificacion.get(13).getDifGoles(),
                            clasificacion.get(14).getDifGoles(), clasificacion.get(15).getDifGoles(),
                            clasificacion.get(16).getDifGoles(), clasificacion.get(17).getDifGoles(),
                            clasificacion.get(18).getDifGoles(), clasificacion.get(19).getDifGoles()));

                        grafico.drawChart(conf);

                        row2.addComponent(grafico, 1, 1);
                        row2.setColumnExpandRatio(1, 1.5f);
                    }
                }
            }
        });
    }

    @WebServlet(urlPatterns = "/Grafico/*", name = "GraficoServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Grafico.class, productionMode = false)
    public static class GraficoServlet extends VaadinServlet {
    }
}
