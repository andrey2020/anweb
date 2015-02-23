/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.andreynikolaev.anweb.component;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIGraphic;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import net.andreynikolaev.anweb.db.Skills;
import net.andreynikolaev.anweb.db.SkillsGroup;

import org.primefaces.component.graphicimage.GraphicImage;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.panel.Panel;


/**
 *
 * @author andrey
 */

@FacesComponent(value = "net.andreynikolaev.component.SkillsPanel", createTag = true, tagName = "skillsPanel")
public final class SkillsPanel extends Panel{
   
    public static final String COMPONENT_TYPE = "net.andreynikolaev.anweb.component.SkillsPanel";
    
    private List<Skills> listSkill;
    private List<SkillsGroup> listSkillGroup;
    private String value = null;
    private String header = "";
    HtmlPanelGrid panelGrid = new HtmlPanelGrid();
    
    public SkillsPanel(){
        setTransient(true);
        UIGraphic gg =  new UIGraphic();
        
    }
 
    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        
        this.listSkillGroup = (List<SkillsGroup>)getAttributes().get("groupList");
        this.header = (String) getAttributes().get("header");
        ResponseWriter writer = context.getResponseWriter();
        /*writer.write("                    <svg width='420px' height='420px' viewBox=\"0 0 100 100\" preserveAspectRatio=\"xMidYMid\" >\n" +
"                        <defs>\n" +
"                         <radialGradient id=\"grad1\" cx=\"50%\" cy=\"50%\" r=\"50%\" fx=\"50%\" fy=\"50%\">\n" +
"                           <stop class=\"stopColorBody\" offset=\"0%\" style=\"stop-opacity:1\" />\n" +
"                           <stop class=\"stopColorMain\" offset=\"100%\" style=\"stop-opacity:0.0\" />\n" +
"                         </radialGradient>\n" +
"                       </defs>\n" +
"                     <circle  fill=\"url(#grad1)\" cx=\"50\" cy=\"50\" r=\"50\"/>\n" +
"                        <g transform=\"translate(-20,-20)\">\n" +
"                          <path class=\"fillSecondColor\" d=\"M79.9,52.6C80,51.8,80,50.9,80,50s0-1.8-0.1-2.6l-5.1-0.4c-0.3-2.4-0.9-4.6-1.8-6.7l4.2-2.9c-0.7-1.6-1.6-3.1-2.6-4.5 L70,35c-1.4-1.9-3.1-3.5-4.9-4.9l2.2-4.6c-1.4-1-2.9-1.9-4.5-2.6L59.8,27c-2.1-0.9-4.4-1.5-6.7-1.8l-0.4-5.1C51.8,20,50.9,20,50,20 s-1.8,0-2.6,0.1l-0.4,5.1c-2.4,0.3-4.6,0.9-6.7,1.8l-2.9-4.1c-1.6,0.7-3.1,1.6-4.5,2.6l2.1,4.6c-1.9,1.4-3.5,3.1-5,4.9l-4.5-2.1 c-1,1.4-1.9,2.9-2.6,4.5l4.1,2.9c-0.9,2.1-1.5,4.4-1.8,6.8l-5,0.4C20,48.2,20,49.1,20,50s0,1.8,0.1,2.6l5,0.4 c0.3,2.4,0.9,4.7,1.8,6.8l-4.1,2.9c0.7,1.6,1.6,3.1,2.6,4.5l4.5-2.1c1.4,1.9,3.1,3.5,5,4.9l-2.1,4.6c1.4,1,2.9,1.9,4.5,2.6l2.9-4.1 c2.1,0.9,4.4,1.5,6.7,1.8l0.4,5.1C48.2,80,49.1,80,50,80s1.8,0,2.6-0.1l0.4-5.1c2.3-0.3,4.6-0.9,6.7-1.8l2.9,4.2 c1.6-0.7,3.1-1.6,4.5-2.6L65,69.9c1.9-1.4,3.5-3,4.9-4.9l4.6,2.2c1-1.4,1.9-2.9,2.6-4.5L73,59.8c0.9-2.1,1.5-4.4,1.8-6.7L79.9,52.6 z M50,65c-8.3,0-15-6.7-15-15c0-8.3,6.7-15,15-15s15,6.7,15,15C65,58.3,58.3,65,50,65z\" >\n" +
"                            <animateTransform attributeName=\"transform\" type=\"rotate\" from=\"90 50 50\" to=\"0 50 50\" dur=\"1s\" repeatCount=\"indefinite\"></animateTransform>\n" +
"                          </path>\n" +
"                        </g>\n" +
"                        <g transform=\"translate(20,20) rotate(15 50 50)\">\n" +
"                          <path class=\"fillMainColor\" d=\"M79.9,52.6C80,51.8,80,50.9,80,50s0-1.8-0.1-2.6l-5.1-0.4c-0.3-2.4-0.9-4.6-1.8-6.7l4.2-2.9c-0.7-1.6-1.6-3.1-2.6-4.5 L70,35c-1.4-1.9-3.1-3.5-4.9-4.9l2.2-4.6c-1.4-1-2.9-1.9-4.5-2.6L59.8,27c-2.1-0.9-4.4-1.5-6.7-1.8l-0.4-5.1C51.8,20,50.9,20,50,20 s-1.8,0-2.6,0.1l-0.4,5.1c-2.4,0.3-4.6,0.9-6.7,1.8l-2.9-4.1c-1.6,0.7-3.1,1.6-4.5,2.6l2.1,4.6c-1.9,1.4-3.5,3.1-5,4.9l-4.5-2.1 c-1,1.4-1.9,2.9-2.6,4.5l4.1,2.9c-0.9,2.1-1.5,4.4-1.8,6.8l-5,0.4C20,48.2,20,49.1,20,50s0,1.8,0.1,2.6l5,0.4 c0.3,2.4,0.9,4.7,1.8,6.8l-4.1,2.9c0.7,1.6,1.6,3.1,2.6,4.5l4.5-2.1c1.4,1.9,3.1,3.5,5,4.9l-2.1,4.6c1.4,1,2.9,1.9,4.5,2.6l2.9-4.1 c2.1,0.9,4.4,1.5,6.7,1.8l0.4,5.1C48.2,80,49.1,80,50,80s1.8,0,2.6-0.1l0.4-5.1c2.3-0.3,4.6-0.9,6.7-1.8l2.9,4.2 c1.6-0.7,3.1-1.6,4.5-2.6L65,69.9c1.9-1.4,3.5-3,4.9-4.9l4.6,2.2c1-1.4,1.9-2.9,2.6-4.5L73,59.8c0.9-2.1,1.5-4.4,1.8-6.7L79.9,52.6 z M50,65c-8.3,0-15-6.7-15-15c0-8.3,6.7-15,15-15s15,6.7,15,15C65,58.3,58.3,65,50,65z\" >\n" +
"                            <animateTransform attributeName=\"transform\" type=\"rotate\" from=\"0 50 50\" to=\"90 50 50\" dur=\"1s\" repeatCount=\"indefinite\"></animateTransform>\n" +
"                          </path>\n" +
"                        </g>\n" +
"                    </svg>");
        */
        skillsMake();

        
    }
 
    public void skillsMake(){
        panelGrid = new HtmlPanelGrid();
        this.setHeader("skills");
        this.setHeader(header);
        
        panelGrid.setColumns(2);
        panelGrid.setColumnClasses("VerticalTop, VerticalTop");
        //this.getChildren().clear();
        

        
        listSkillGroup.stream().filter(sg -> sg.getShow())
                .sorted((sg1, sg2) -> Integer.compare(sg1.getShowPosition(), sg2.getShowPosition()))
                .forEach((skillGroup) -> {
                    panelGrid.getChildren().add(generateGroupPanel(skillGroup.getGroupName(), skillGroup.getSkills()));
        });

        this.getChildren().add(panelGrid);
    }
    
    private HtmlPanelGrid generateGroupPanel(String title, List<Skills> skillsList){
        HtmlPanelGrid panelGrid = new HtmlPanelGrid();
        panelGrid.setColumns(1);
        
        GraphicImage punktLineG = new GraphicImage();
        punktLineG.setLibrary("img");
        punktLineG.setName("punktLine.svg");
        punktLineG.setStyle("margin-left: 52px; width: 255px;");
        
        //------------------------------------------
        
        HtmlPanelGrid skillsPanelGrid = new HtmlPanelGrid();
        skillsPanelGrid.setStyle("margin-left: auto; text-align: end");
        skillsPanelGrid.setColumns(8);
        
        HtmlPanelGrid groupTitlePanel = new HtmlPanelGrid();
        groupTitlePanel.setColumns(2);
        groupTitlePanel.setStyle("margin-bottom: -16px;");
        GraphicImage plusG = new GraphicImage();
        plusG.setLibrary("img");
        plusG.setName("plus.svg");
        plusG.setStyle("margin-right: 10px;");
        
        GraphicImage line = new GraphicImage();
        line.setLibrary("img");
        line.setName("lineSkills.svg");
        line.setStyle("margin-left: 30px; width: 255px;");
        
        groupTitlePanel.getChildren().add(plusG);
        groupTitlePanel.getChildren().add(getTitleLabel(title));
        
        Panel pp = new Panel();
        pp.setStyle("border: none !important;");

        skillsList.stream().filter(s -> s.getShow())
                .sorted((s1, s2) -> Integer.compare(s1.getShowPosition(), s2.getShowPosition()))
                .forEach((mSkillsList) -> {
            //skillsList.stream().forEach((mSkillsList) -> {
            
            if ((mSkillsList.getRating() != null) && (mSkillsList.getRating() > 0)) {
                OutputLabel itemLabel = getItemLabel(mSkillsList.getName());
                skillsPanelGrid.getChildren().add(itemLabel);
                for(int i=0;i<mSkillsList.getRating();i++){
                    GraphicImage punktActiveG = new GraphicImage();
                    punktActiveG.setLibrary("img");
                    punktActiveG.setName("circle.png");

                    punktActiveG.setWidth("11px");
                    punktActiveG.setHeight("auto");
                    skillsPanelGrid.getChildren().add(punktActiveG);
                }
                for(int i=0;i<7-mSkillsList.getRating();i++){
                    GraphicImage punktActiveG = new GraphicImage();
                    punktActiveG.setLibrary("img");
                    punktActiveG.setName("circle.png");
                    punktActiveG.setStyle("opacity: 0.4;");
                    punktActiveG.setWidth("11px");
                    punktActiveG.setHeight("auto");
                    skillsPanelGrid.getChildren().add(punktActiveG);
                }
            }
            else {
                for (int i = 0; i < 8; i++) {
                    skillsPanelGrid.getChildren().add(getItemLabel(" "));
                }
            }
        });
                
        pp.getChildren().add(groupTitlePanel);
        pp.getChildren().add(line);
        pp.getChildren().add(skillsPanelGrid);
        
        //------------------------------------------
        
        panelGrid.getChildren().add(pp);
        panelGrid.getChildren().add(punktLineG);
        
       return panelGrid;
    }

    private OutputLabel getTitleLabel(String title){
        OutputLabel lb = new OutputLabel();
        title = title.toUpperCase();
        lb.setStyle("font-size: 100%;");
        lb.setValue(title);
        return lb;
    }

    private OutputLabel getItemLabel(String title){
        OutputLabel lb = new OutputLabel();    
        lb.setStyle("font-size: 9pt; padding-right: 5px;");
        lb.setValue(title);
        return lb;
    }

    private String getValue() {
        return value;
    }

    
    
   
}  
