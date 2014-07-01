package fr.toutatice.portail.cms.nuxeo.portlets.bridge;
		

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.nuxeo.ecm.automation.client.model.Document;
import org.osivia.portal.api.urls.Link;

import fr.toutatice.portail.cms.nuxeo.api.NuxeoController;
import fr.toutatice.portail.cms.nuxeo.api.VocabularyHelper;


public class Formater extends AbstractFormater 
{

	public static String formatSource(Document doc) throws ParseException 
	{
		//String source2 = VocabularyHelper.getVocabularyLabel("ctx" + "sourcesOrganisationnelles" + doc.getProperties().getString("acr:publisher"));
		String source =doc.getProperties().getString("acr:publisher");

		if (source == null)
			source = "aucune";

		return source;
	}
	
	public static String formatCreator(Document doc) throws ParseException 
	{
		String creator =doc.getProperties().getString("dc:creator");

		if (creator == null)
			creator = "aucun";

		return creator;
	}
	
	public static String formatDate(Document doc) throws ParseException {

		String sDate = doc.getProperties().getString("dc:issued");
		if (sDate == null)
			sDate = doc.getProperties().getString("dc:modified");
		if (sDate == null)
			sDate = doc.getProperties().getString("dc:created");

		if (sDate == null)
			return ("");

		sDate = sDate.substring(0, 10);

		DateFormat inputFormater = new SimpleDateFormat("yyyy-MM-dd");
		Date date = inputFormater.parse(sDate);

		DateFormat outputFormater = DateFormat.getDateInstance(DateFormat.LONG, Locale.FRENCH);

		return outputFormater.format(date);
	}
	
	public static String formatDateCourt(Document doc) throws ParseException {

		String sDate = doc.getProperties().getString("dc:issued");
		if (sDate == null)
			sDate = doc.getProperties().getString("dc:modified");
		if (sDate == null)
			sDate = doc.getProperties().getString("dc:created");

		if (sDate == null)
			return ("");

		sDate = sDate.substring(0, 10);

		DateFormat inputFormater = new SimpleDateFormat("yyyy-MM-dd");
		Date date = inputFormater.parse(sDate);

		DateFormat outputFormater = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRENCH);

		return outputFormater.format(date);
	}
	
	public static String formatDateCourtYearLong(Document doc) throws ParseException {

		String sDate = doc.getProperties().getString("dc:issued");
		if (sDate == null)
			sDate = doc.getProperties().getString("dc:modified");
		if (sDate == null)
			sDate = doc.getProperties().getString("dc:created");

		if (sDate == null)
			return ("");

		sDate = sDate.substring(0, 10);

		DateFormat inputFormater = new SimpleDateFormat("yyyy-MM-dd");
		Date date = inputFormater.parse(sDate);

		DateFormat outputFormater = new SimpleDateFormat("dd/MM/yyyy");		
		
		return outputFormater.format(date);
	}
	
	public static String formatDatePublication(Document doc) throws ParseException {

		String sDate = doc.getProperties().getString("dc:issued");		

		if (sDate == null)
			return ("");

		sDate = sDate.substring(0, 10);

		DateFormat inputFormater = new SimpleDateFormat("yyyy-MM-dd");
		Date date = inputFormater.parse(sDate);

		DateFormat outputFormater = DateFormat.getDateInstance(DateFormat.LONG, Locale.FRENCH);

		return outputFormater.format(date);
	}
	
	public static String formatExtrait(Document doc) throws ParseException 
	{
		String extrait = doc.getProperties().getString("annonce:resume");

		if (extrait == null)
			extrait = "";

		return extrait;
	}
	
	public static String formatContenu(Document doc) throws ParseException 
	{
		String contenu = doc.getProperties().getString("note:note");

		if (contenu == null)
			contenu = "";

		return contenu;
	}
	
	/*
	public static String formatContenu(Document doc) throws ParseException 
	{
		String contenu = doc.getProperties().getString("note:note");

		if (contenu == null)
			contenu = "";

		return contenu;
	}
	*/
	
	public static String formatPictureLink( Link link, Document doc, String extraClassName, String title, String src)	{

		
		String linkClassName = extraClassName;

		if( link.isDownloadable())	
			linkClassName += " osivia-link-download";
			
		if(	link.isExternal())
			linkClassName += " osivia-link-external";
		
		String target = formatTarget(link);
		
		//String res = "<a "+ target+" href=\""+ link.getUrl()+"\" class=\""+linkClassName+"\" title=\"" + title + "\"><span>"+doc.getTitle()+"</span></a>";
		
		String res = "<a "+ target+" href=\""+ link.getUrl()+"\" class=\""+linkClassName+"\" title=\"" + title + "\"> <div class=\"vignette\"><img class=\"vignette\" src=\""+ src + "\" /></div> </a>";
		
		return res;
		
	}
	
	public static String formatVocabulary3Level(NuxeoController ctx,Document doc,String vocParent,String vocChild1,String vocChild2,String xpath) 
			throws Exception 
	{
		StringBuffer stb = new StringBuffer();
		String value = doc.getProperties().getString(xpath);
		if(value!=null){
		String[] tab = value.split("/");
		
		stb.append(VocabularyHelper.getVocabularyLabel(ctx, vocParent, tab[0]));

		if (tab.length > 1) {
			stb.append(" / ");
			stb.append(VocabularyHelper.getVocabularyLabel(ctx, vocChild1, tab[1]));
		}
		if (tab.length > 2 && vocChild2!=null ) {
			stb.append(" / ");
			stb.append(VocabularyHelper.getVocabularyLabel(ctx, vocChild2, tab[2]));
		}
		}
		return stb.toString();
	}

}
