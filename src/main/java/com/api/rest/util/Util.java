package com.api.rest.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
	
	public static Object retirarReferenciasCiclicasJavassist(Object object) throws Exception
	{
		Node<Object> node = new Node<Object>(object);
		retirarReferenciasCiclicasJavassist(object, node, true);
		return object;
	}
	
	public static Object retirarReferenciasCiclicasJavassist(Object object, boolean ciclica) throws Exception
	{
		Node<Object> node = new Node<Object>(object);
		retirarReferenciasCiclicasJavassist(object, node, ciclica);
		return object;
	}
	
	@SuppressWarnings("rawtypes")
	private static void retirarReferenciasCiclicasJavassist(Object object, Node<Object> node, Boolean blCiclica) throws Exception
	{
		ArrayList<Field> fields = new ArrayList<Field>();
		if (object.getClass().getDeclaredFields() != null && object.getClass().getDeclaredFields().length > 0)
			fields.addAll(Arrays.asList(object.getClass().getDeclaredFields()));
		if (object.getClass().getSuperclass().getDeclaredFields() != null && object.getClass().getSuperclass().getDeclaredFields().length > 0)
			fields.addAll(Arrays.asList(object.getClass().getSuperclass().getDeclaredFields()));
		
		for (Field field : fields)
		{
			field.setAccessible(true);
			try
			{
				if (field.get(object) == null || Modifier.isFinal(field.getModifiers()))
					continue;
				if (field.getType().isPrimitive() || field.getType().equals(String.class) || field.getType().equals(Date.class) || field.getType().equals(Timestamp.class) || field.getType().getSuperclass().equals(Number.class) || field.getType().equals(Boolean.class) || field.getType().isEnum() || field.getType().isArray())
					continue;
				if (field.get(object).getClass().getName().indexOf("javassist") > 0)
					field.set(object, null);
				if (field.getType().equals(List.class) && field.getType().equals(Set.class))
				{
					Iterator iterator = null;
					if (field.getType().equals(List.class))
						iterator = ((List) field.get(object)).iterator();
					else
						iterator = ((Set) field.get(object)).iterator();
					for (; iterator.hasNext();)
					{
						Object objectLista = (Field) iterator.next();
						Node<Object> nodeFilho = new Node<Object>(objectLista);
						node.addChild(nodeFilho);
						retirarReferenciasCiclicasJavassist(objectLista, nodeFilho, blCiclica);
					}
				}
				else
				{
					boolean existe = false;
					Node<Object> parent = node.getParent();
					while (parent != null)
					{
						if (existe = field.get(object).equals(parent.getData()))
						{
							if (blCiclica)
								field.set(object, null);
							break;
						}
						
						parent = parent.getParent();
					}
					
					if (!existe)
					{
						Node<Object> nodeFilho = new Node<Object>(field.get(object));
						node.addChild(nodeFilho);
						retirarReferenciasCiclicasJavassist(field.get(object), nodeFilho, blCiclica);
					}
					else
						continue;
					
				}
			}
			catch (Exception e)
			{
				field.set(object, null);
			}
		}
	}
	
}
