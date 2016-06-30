
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.MaskFormatter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.primefaces.showcase.domain.Player;



public class TesteMain {
	
	private static List<Player> players;

	private static List<Integer> years;
	
	private static String[] playerNames;

	private static final String TIME24HOURS_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
	
	public static void main(String[] args) {
	    //System.out.println(getSomaMediaIniciacaoETempoExecucao("01:00:00"));
	    System.out.println(240000L/235959L);
	}
	
	
	/**
	 * Calcula o tempo que deve ser acrescentado a data limite de acordo com a mediaIniciacao e mediaTempoExecucao.
	 * @param horaLimite 
	 * @param ConsolidacaoAjob:  cAjob
	 * @return Integer 
	 */
	private static Integer getSomaMediaIniciacaoETempoExecucao(String horaLimite) {
		
		Long mediaInicicao = Long.valueOf("235620");
		Long mediaTempoExecucao = Long.valueOf("000632");
		
		
		
		if ("00:00:00".equals(horaLimite)) {
			return 1;
		}
			
		if ((mediaInicicao + mediaTempoExecucao) <= Long.valueOf("235959")) {
			return 0;
		}
		Long tempo = (mediaInicicao + mediaTempoExecucao) / Long.valueOf("235959");
		if (tempo > 0) {
			return tempo.intValue();
		}
		return 0;
	}
	
	
	public static BigDecimal formatarDuasCasas(BigDecimal value) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setMinimumFractionDigits(2);
		DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
		dfs.setDecimalSeparator('.');
		df.setDecimalFormatSymbols(dfs);
		df.setRoundingMode(RoundingMode.DOWN);
		String saida = df.format(value);
		return new BigDecimal(saida);
	}
	
	
	
	
	public static boolean validaHora(String time){
		Pattern pattern = Pattern.compile(TIME24HOURS_PATTERN);
		Matcher matcher = pattern.matcher(time);
		return matcher.matches();
	}

	private static String formatStr(String i) {
		try {
			MaskFormatter m = new MaskFormatter("##:##:##");
			m.setPlaceholderCharacter('0');
			return m.valueToString(i);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return StringUtils.leftPad(i, 2, "0");
	}

	/**
	 * @return
	 */
	private static List<String> preenche() {
		List<String> valores = new ArrayList<String>();
		for (int i = 1; i < 5; i++) {
			valores.add("Valor "+i);
			valores.add("Valor "+i);
		}
		return valores;
	}
	
	public static String formataSiglaFormatado(String sigla) {
		StringBuilder s = new StringBuilder();
		if (sigla != null) {
			if (sigla.length() > 12) {
				s.append(sigla.substring(0, 12));
				s.append("...");
			} else {
				s.append(sigla);
			}
		}
		return s.toString();
	}
	
	/**
	 * Calcular a diferenca em minutos entre d1 e d2.
	 * 
	 * <p>Ex: d1 = <b>18/12/2015 18:45</b> e d2 = <b>18/12/2015 18:30</b> 
	 * 
	 * <p>d1 - d2 --> 15 Minutos
	 * 
	 * @param d1
	 * @param d2
	 * @return diferenca em minutos entre as datas.
	 */
	public static BigDecimal difDataMinutos(Date d1, Date d2) {
		BigDecimal divisorMinutos = new BigDecimal(60000);
		try {
			BigDecimal diferenca = new BigDecimal(d1.getTime() - d2.getTime());
			return  diferenca.divide(divisorMinutos, 2, RoundingMode.HALF_UP); //new BigDecimal(diferenca / 1000 / 60);
		} catch (NullPointerException npe) {
			return null;
		}
	}
	
	public static Date parseDataHoraMinutoSegundo2(String horaMinutoSegundo) {
		Calendar cal = Calendar.getInstance(new Locale("pt", "BR"));
		int horas = Integer.parseInt(horaMinutoSegundo.substring(0, 2));
		int minutos = Integer.parseInt(horaMinutoSegundo.substring(3, 5));
		int segundos = Integer.parseInt(horaMinutoSegundo.substring(6, 8));
		cal.set(Calendar.HOUR_OF_DAY, horas);
		cal.set(Calendar.MINUTE, minutos);
		cal.set(Calendar.SECOND, segundos);
		return cal.getTime();
	}
	
	public static Date parseDataHoraMinutoSegundo(String horaMinutoSegundo) {
		Calendar calDataAtual = Calendar.getInstance(new Locale("pt", "BR"));
		Calendar cal2 = Calendar.getInstance(new Locale("pt", "BR"));
		cal2.setTime(parseData(horaMinutoSegundo, "HH:mm:ss"));
		calDataAtual.set(Calendar.HOUR_OF_DAY, cal2.get(Calendar.HOUR_OF_DAY));
		calDataAtual.set(Calendar.MINUTE, cal2.get(Calendar.MINUTE));
		calDataAtual.set(Calendar.SECOND, cal2.get(Calendar.SECOND));
		return calDataAtual.getTime();
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
    public static boolean isBlank(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        }
        return true;
    }
	
	public static Date parseData(String data, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, new Locale("pt", "BR"));
		try {
			return simpleDateFormat.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean isCampoValido(String texto){
		Pattern p = Pattern.compile("[a-zA-Z0-9]*");
		Matcher m = p.matcher(texto);
		//return m.matches();
		return texto.matches("[a-zA-Z0-9]+");
	}
	
	public static boolean validaAtributo(String nome) {
		//Pattern pattern = Pattern.compile(regex);
		//Matcher matcher = pattern.matcher(nome);
		 //System.out.println(nome.replaceAll(regex, "A"));
//		return matcher.matches();
		return StringUtils.isAlphanumericSpace(nome);
	}
	
	public static List<String> getSiglaAtributosFormula(String formula) {
		String[] atributosSplit = null;
		List<String> siglasAttr = preenche();
		if (StringUtils.isNotBlank(formula)) {
			try {
				atributosSplit = formula.split("[^a-zA-Z0-9]");
			} catch (Exception e) {
				return null;
			}
			for (String sigla : atributosSplit) {
				if (StringUtils.isNotBlank(sigla) && !NumberUtils.isNumber(sigla.trim())) {
					siglasAttr.add(sigla);
				}
			}
		}
		return siglasAttr;
	}
	
	
	public static String[] recuperaUltimosTresMeses() {
		String[] mesAnoAnterior = new String[3];
		for (int i = 1; i <= mesAnoAnterior.length; i++) {
			Date dtAnt = recuperaDataPrimeiroDia(-i);
			mesAnoAnterior[i-1] = formataDataMesAno(dtAnt);
		}
		return mesAnoAnterior;
	}
	
	/**
	 * Formata Data para String no formato MMyyyy.
	 * 
	 * @param dataFormat
	 * @return
	 */
	public static String formataDataMesAno(Date dataFormat) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMyyyy",
				new Locale("pt", "BR"));
		return simpleDateFormat.format(dataFormat.getTime());
	}
	
	public static Date recuperaDataPrimeiroDia(int qtdeMes) {
		Calendar c = Calendar.getInstance(new Locale("pt", "BR"));
		if (qtdeMes != 0) {
			c.add(Calendar.MONTH, qtdeMes);
		}
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}
	
	public static String getDescricao() {
		String sigla = null;
		String nome = null;
		return sigla +" - "+ nome;
	}




	public static void init() {
		playerNames = new String[10];
        playerNames[0] = "Lionel Messi";
        playerNames[1] = "Cristiano Ronaldo";
        playerNames[2] = "Arjen Robben";
        playerNames[3] = "Franck Ribery";
        playerNames[4] = "Ronaldinho";
        playerNames[5] = "Luis Suarez";
        playerNames[6] = "Sergio Aguero";
        playerNames[7] = "Zlatan Ibrahimovic";
        playerNames[8] = "Neymar Jr";
        playerNames[9] = "Andres Iniesta";
		years = new ArrayList<Integer>();
        years.add(2010);
        years.add(2011);
        years.add(2012);
        years.add(2013);
        years.add(2014);
		players = new ArrayList<Player>();
		for(int i = 0; i < 10; i++) {
			players.add(new Player(playerNames[i],"Apelido "+i, generateRandomGoalStatsData()));
		}
	}
	
	 private static Map<Integer,Integer> generateRandomGoalStatsData() {
	        Map<Integer,Integer> stats = new LinkedHashMap<Integer, Integer>();
	        for (int i = 0; i < 5; i++) {
	            stats.put(years.get(i), getRandomGoals());
	        }
	        return stats;
	    }

	private static int getRandomGoals() {
		return (int) (Math.random() * 50);
	}

}
