package com.api.rest.util.dao;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HibernateUtil
{
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void removeNotInsertableUpdatableEntity(Object entity) throws Exception
	{
		if (entity != null)
		{
			Class entityClass = entity.getClass();
			// if (entityClass.getSuperclass() != null && entityClass.getSuperclass().equals(UsuarioAuditoriaTO.class))
			// {
			// entityClass.getMethod("setUsuarioInclusaoTO", UsuarioVTO.class).invoke(entity, new Object[] { null });
			// entityClass.getMethod("setUsuarioAlteracaoTO", UsuarioVTO.class).invoke(entity, new Object[] { null });
			// }
			List<Field> camposNaoInseridosOUNaoAtualizados = new ArrayList<Field>();
			Field fields[] = entityClass.getDeclaredFields();
			for (int i = 0; i < fields.length; i++)
			{
				Field campo = fields[i];
				Annotation annotations[] = campo.getAnnotations();
				boolean anulaObjeto = false;
				for (int j = 0; j < annotations.length; j++)
				{
					Annotation annotation = annotations[j];
					if (annotation.toString().indexOf("insertable=false") > -1 && annotation.toString().indexOf("updatable=false") > -1)
					{
						camposNaoInseridosOUNaoAtualizados.add(campo);
						anulaObjeto = true;
						break;
					}
				}
				if (!anulaObjeto)
				// if (campo.getType().equals(Set.class) && !(entity instanceof UsuarioTO && ((UsuarioTO) entity).getTpUsuario().equals(TipoUsuario.CARTAO)))
				{
					String nome = campo.getName().substring(0, 1).toUpperCase() + campo.getName().substring(1);
					Set lista = (Set) entityClass.getMethod("get" + nome).invoke(entity);
					if (lista != null)
						for (Object object : lista)
						{
							removeNotInsertableUpdatableEntity(object);
						}
				}
			}
			Method methods[] = entityClass.getMethods();
			for (int i = 0; i < methods.length; i++)
			{
				Method method = methods[i];
				for (Field field : camposNaoInseridosOUNaoAtualizados)
				{
					if (method.getName().equalsIgnoreCase("set" + field.getName()))
					{
						method.invoke(entity, new Object[] { null });
						break;
					}
				}
			}
		}
	}
	
}
