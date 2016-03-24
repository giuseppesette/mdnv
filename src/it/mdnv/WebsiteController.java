package it.mdnv;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
//import org.hibernate.Session;
//import org.hibernate.Transaction;

/**
 *
 * @author vagrawal
 */
@ManagedBean(name = "WebsiteController")
@SessionScoped
public class WebsiteController implements java.io.Serializable
{

//    private List<ResourceCorpWebsite> websiteList = null;
//    private ResourceCorpWebsite corpwebsite;
    private String rendered;

    public WebsiteController()
    {
    }

    /**
     * @return the corpwebsite
     *
    public ResourceCorpWebsite getCorpwebsite()
    {
        try
        {
            Session session = HibernateUtil.getSessionFactory2().openSession();
            Transaction tx = session.beginTransaction();
            this.websiteList = session.createQuery("from ResourceCorpWebsite").list();
            this.corpwebsite = this.websiteList.get(0);
            tx.commit();
            session.close();
        }
        catch (Exception ex)
        {
            GeneralHelper ge = new GeneralHelper();
            ge.alertexceptions("Exception in PropertiesDA.getproperties()", ex);
        }
        finally
        {
            return this.corpwebsite;
        }
    }

    /**
     * @param corpwebsite the corpwebsite to set
     *
    public void setCorpwebsite(ResourceCorpWebsite corpwebsite)
    {
        this.corpwebsite = corpwebsite;
    }

    /**
     * @return the rendered
     */
    public String getRendered()
    {
        return rendered;
    }

    /**
     * @param rendered the rendered to set
     */
    public void setRendered(String rendered)
    {
        this.rendered = rendered;
    }

    public void savePropertyWebsite()
    {
    	
		System.out.println("[MenuView][logout] - invalidateSession!!!");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		try
        {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/logout.xhtml?faces-redirect=true");
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
//            GeneralHelper ge = new GeneralHelper();
//            ge.alertexceptions("Exception in wProertyWebsiteDA.sacepropertywebsite()", ex);
        }
    }
    /*
        try
        {
            Session session2 = HibernateUtil.getSessionFactory2().openSession();
            Transaction tx = session2.beginTransaction();
            session2.update(this.corpwebsite);
            tx.commit();
            session2.refresh(this.corpwebsite);
            session2.close();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Updated"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("editor.xhtml");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Updated"));
        }
        catch (Exception ex)
        {
            GeneralHelper ge = new GeneralHelper();
            ge.alertexceptions("Exception in wProertyWebsiteDA.sacepropertywebsite()", ex);
        }
     */

    }