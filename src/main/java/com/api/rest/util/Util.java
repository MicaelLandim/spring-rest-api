package com.api.rest.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
public class Util
{
	
	@SuppressWarnings("rawtypes")
	public static Enum getEnumValor(Class classeEnum, Object valor) throws Exception
	{
		try
		{
			Object[] enums = classeEnum.getEnumConstants();
			if (enums != null)
				for (Object valorEnum : enums)
				{
					if (valorEnum.getClass().getMethod("getValor").invoke(valorEnum).toString().equals(String.valueOf(valor)))
						return (Enum) valorEnum;
				}
		}
		catch (Exception e)
		{
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public static Enum getEnumValorEnum(Class classeEnum, Object valor) throws Exception
	{
		try
		{
			Object[] enums = classeEnum.getEnumConstants();
			if (enums != null)
				for (Object valorEnum : enums)
				{
					if (valorEnum.getClass().getMethod("name").invoke(valorEnum).toString().equals(valor))
						return (Enum) valorEnum;
				}
		}
		catch (Exception e)
		{
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public static Enum getEnumValorDescricao(Class classeEnum, Object valor) throws Exception
	{
		try
		{
			Object[] enums = classeEnum.getEnumConstants();
			if (enums != null)
				for (Object valorEnum : enums)
				{
					if (valorEnum.getClass().getMethod("toString").invoke(valorEnum).toString().equals(valor))
						return (Enum) valorEnum;
				}
		}
		catch (Exception e)
		{
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean existeEnum(Enum[] valores, Enum valor) throws Exception
	{
		for (Enum enum1 : valores)
			if (valor.equals(enum1))
				return true;
		return false;
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean existeEnum(Enum[] valores, String valor) throws Exception
	{
		return existeEnum(valores, getEnumValorEnum(valores.getClass().getComponentType(), valor));
	}
	
	@SuppressWarnings("rawtypes")
	public static Integer[] enumArrayToObjectArrayInteger(Enum[] array) throws Exception
	{
		if (array == null)
			return null;
		Integer objects[] = new Integer[array.length];
		for (int i = 0; i < array.length; i++)
		{
			Enum enum1 = array[i];
			objects[i] = (Integer) enum1.getClass().getMethod("getValor").invoke(enum1);
			
		}
		return objects;
	}
	
	public static String retirarAcentos(String palavra)
	{
		if (palavra == null)
			return null;
		palavra = palavra.replaceAll("[ÂÀÁÄÃ]", "A");
		palavra = palavra.replaceAll("[âãàáä]", "a");
		palavra = palavra.replaceAll("[ÊÈÉË]", "E");
		palavra = palavra.replaceAll("[êèéë]", "e");
		palavra = palavra.replaceAll("[ÎÍÌÏ]", "I");
		palavra = palavra.replaceAll("[îíìï]", "i");
		palavra = palavra.replaceAll("[ÔÕÒÓÖ]", "O");
		palavra = palavra.replaceAll("[ôõòóö]", "o");
		palavra = palavra.replaceAll("[ÛÙÚÜ]", "U");
		palavra = palavra.replaceAll("[ûúùü]", "u");
		palavra = palavra.replaceAll("[Ç]", "C");
		palavra = palavra.replaceAll("[ç]", "c");
		palavra = palavra.replaceAll("[ýÿ]", "y");
		palavra = palavra.replaceAll("[Ý]", "Y");
		palavra = palavra.replaceAll("[ñ]", "n");
		palavra = palavra.replaceAll("[Ñ]", "N");
		palavra = palavra.replaceAll("[ºª]", "");
		palavra = palavra.replaceAll("\u00A0", " ");
		palavra = palavra.replaceAll("[']", " ");
		palavra = palavra.replaceAll("\\t", " ");
		palavra = palavra.replaceAll("\\ {2,}", " ");
		return palavra.toUpperCase().trim();
	}
	
	public static String retirarAcentosContraCheque(String palavra)
	{
		if (palavra == null)
			return null;
		palavra = palavra.replaceAll("[ÂÀÁÄÃ]", "A");
		palavra = palavra.replaceAll("[âãàáä]", "a");
		palavra = palavra.replaceAll("[ÊÈÉË]", "E");
		palavra = palavra.replaceAll("[êèéë]", "e");
		palavra = palavra.replaceAll("[ÎÍÌÏ]", "I");
		palavra = palavra.replaceAll("[îíìï]", "i");
		palavra = palavra.replaceAll("[ÔÕÒÓÖ]", "O");
		palavra = palavra.replaceAll("[ôõòóö]", "o");
		palavra = palavra.replaceAll("[ÛÙÚÜ]", "U");
		palavra = palavra.replaceAll("[ûúùü]", "u");
		palavra = palavra.replaceAll("[Ç]", "C");
		palavra = palavra.replaceAll("[ç]", "c");
		palavra = palavra.replaceAll("[ýÿ]", "y");
		palavra = palavra.replaceAll("[Ý]", "Y");
		palavra = palavra.replaceAll("[ñ]", "n");
		palavra = palavra.replaceAll("[Ñ]", "N");
		palavra = palavra.replaceAll("[ºª]", "");
		palavra = palavra.replaceAll("\u00A0", " ");
		palavra = palavra.replaceAll("[']", " ");
		return palavra.trim();
	}
	
	public static String adaptarPalavraUTF8(String palavra)
	{
		if (palavra == null)
			return null;
		// palavra = palavra.replaceAll("\t", "&#09;");
		// palavra = palavra.replaceAll("\n", "&#10;");
		// palavra = palavra.replaceAll("\r", "&#13;");
		// palavra = palavra.replaceAll(" ", "&#32;");
		// palavra = palavra.replaceAll("!", "&#33;");
		// palavra = palavra.replaceAll("\"", "&#34;");
		// palavra = palavra.replaceAll("#", "&#35;");
		// palavra = palavra.replaceAll("$", "&#36;");
		palavra = palavra.replaceAll("%", " ");
		// palavra = palavra.replaceAll("&", "&#38;");
		// palavra = palavra.replaceAll("'", "&#39;");
		// palavra = palavra.replaceAll("\\(", "&#40;");
		// palavra = palavra.replaceAll("\\)", "&#41;");
		// palavra = palavra.replaceAll("\\*", "&#42;");
		palavra = palavra.replaceAll("\\+", " ");
		// palavra = palavra.replaceAll("\\,", "&#44;");
		// palavra = palavra.replaceAll("\\-", "&#45;");
		// palavra = palavra.replaceAll("\\.", "&#46;");
		// palavra = palavra.replaceAll("/", "&#47;");
		// palavra = palavra.replaceAll(":", "&#58;");
		// palavra = palavra.replaceAll(";", "&#59;");
		// palavra = palavra.replaceAll("<", "&#60;");
		// palavra = palavra.replaceAll("=", "&#61;");
		// palavra = palavra.replaceAll(">", "&#62;");
		// palavra = palavra.replaceAll("\\?", "&#63;");
		// palavra = palavra.replaceAll("@", "&#64;");
		// palavra = palavra.replaceAll("\\[", "&#91;");
		// palavra = palavra.replaceAll("\\\\", "&#92;");
		// palavra = palavra.replaceAll("]", "&#93;");
		// palavra = palavra.replaceAll("^", "&#94;");
		// palavra = palavra.replaceAll("_", "&#95;");
		// palavra = palavra.replaceAll("`", "&#96;");
		// palavra = palavra.replaceAll("\\{", "&#123;");
		// palavra = palavra.replaceAll("|", "&#124;");
		// palavra = palavra.replaceAll("\\}", "&#125;");
		palavra = palavra.replaceAll("~", " ");
		return palavra;
	}
	
	public static void main(String[] args)
	{
		System.out.println(Util.retirarAcentos("JOAO   PAULO  GOERSCH SILVA"));
		System.out.println("NOME:ROSA EDENIZA SODRE DOS SANTOS DA SI 07/2022|MATRICULA:0000379            CPF: 043.151.223-00|EMPRESA  :Mateus Supermercados S.A.|CNPJ     : 03.995.515/0037-78|CARGO    :ASSISTENTE ADMINISTRATIVO|NIVEL    :            DAT ADM:  28/01/2013|Proventos/Vantagens:|1001 Horas Normais            14,40       142,15|1012 Horas Férias Diurnas    205,20     1.990,04|1034 Horas Extras c/ 50%       0,29         7,03|1065 DSR Reflexo H.Extras      0,21         3,52|1134 Média Horas Extras Féri  13,53       134,63|1140 1/3 Férias                0,00       708,22|1142 Diferença de Férias       0,00        39,80|Total Proventos/Vantagens:  R$          3.025,39||Descontos:|1281 Desconto Adto Férias      0,00     2.517,82|1301 INSS Férias              12,00       255,01|1302 INSS                     12,00        17,03|1308 IRRF Férias               7,50        60,06|2361 P. Saúde Hapvida Titula   0,00        98,17|Total Descontos:            R$          2.948,09||Liquido:                    R$             77,30|Base FGTS      3.025,39|Base IRRF        152,70|Base Inss      3.025,39||CRÉDITO CARTÃO CREDNOSSO ALIMENTAÇÃO:     102,00");
		System.out.println(Util.retirarAcentosContraCheque("NOME:ROSA EDENIZA SODRE DOS SANTOS DA SI 07/2022|MATRICULA:0000379            CPF: 043.151.223-00|EMPRESA  :Mateus Supermercados S.A.|CNPJ     : 03.995.515/0037-78|CARGO    :ASSISTENTE ADMINISTRATIVO|NIVEL    :            DAT ADM:  28/01/2013|Proventos/Vantagens:|1001 Horas Normais            14,40       142,15|1012 Horas Férias Diurnas    205,20     1.990,04|1034 Horas Extras c/ 50%       0,29         7,03|1065 DSR Reflexo H.Extras      0,21         3,52|1134 Média Horas Extras Féri  13,53       134,63|1140 1/3 Férias                0,00       708,22|1142 Diferença de Férias       0,00        39,80|Total Proventos/Vantagens:  R$          3.025,39||Descontos:|1281 Desconto Adto Férias      0,00     2.517,82|1301 INSS Férias              12,00       255,01|1302 INSS                     12,00        17,03|1308 IRRF Férias               7,50        60,06|2361 P. Saúde Hapvida Titula   0,00        98,17|Total Descontos:            R$          2.948,09||Liquido:                    R$             77,30|Base FGTS      3.025,39|Base IRRF        152,70|Base Inss      3.025,39||CRÉDITO CARTÃO CREDNOSSO ALIMENTAÇÃO:     102,00"));
	}
}
