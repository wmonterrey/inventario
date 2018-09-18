INSERT INTO `inv_roles` (`ROL`) VALUES ('ROLE_SUPER');
INSERT INTO `inv_roles` (`ROL`) VALUES ('ROLE_ADMIN');
INSERT INTO `inv_roles` (`ROL`) VALUES ('ROLE_TRANS');
INSERT INTO `inv_roles` (`ROL`) VALUES ('ROLE_CATALOG');
INSERT INTO `inv_roles` (`ROL`) VALUES ('ROLE_ADD_ALIC');
INSERT INTO `inv_roles` (`ROL`) VALUES ('ROLE_SEND_ALIC');
INSERT INTO `inv_roles` (`ROL`) VALUES ('ROLE_USE_ALIC');
INSERT INTO `inv_roles` (`ROL`) VALUES ('ROLE_EDIT_ALIC');
INSERT INTO `inv_roles` (`ROL`) VALUES ('ROLE_DEL_ALIC');
INSERT INTO `inv_roles` (`ROL`) VALUES ('ROLE_TEMP');

INSERT INTO `inv_usuarios_sistema` (`NOMBRE_USUARIO`, `CUENTA_SINEXPIRAR`, `CUENTA_SINBLOQUEAR`, `DESCRIPCION`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `CREDENCIAL_SINEXPIRAR`, `CORREO_ELECTRONICO`, `HABILITADO`, `FECHA_ULTACC`, `FECHA_ULTMODCRED`, `FECHA_ULTMOD`, `USUARIO_ULTMOD`, `CONTRASENA`) VALUES ('admin', '', '', 'Administrador', '2014-09-20 08:31:00', 'admin', '', 'admincndr@minsa.gob.ni', '', '2014-11-16 10:05:40', NULL, '2014-11-13 17:58:06', 'admin', '6c36dc262b0e44be5811c2296669fc65643aec9dcaa4a76501e0a9508b633fd01ee59a207f8c6d68');
INSERT INTO `inv_usuarios_sistema` (`NOMBRE_USUARIO`, `CUENTA_SINEXPIRAR`, `CUENTA_SINBLOQUEAR`, `DESCRIPCION`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `CREDENCIAL_SINEXPIRAR`, `CORREO_ELECTRONICO`, `HABILITADO`, `FECHA_ULTACC`, `FECHA_ULTMODCRED`, `FECHA_ULTMOD`, `USUARIO_ULTMOD`, `CONTRASENA`) VALUES ('william', '', '', 'William', '2014-09-20 08:31:00', 'admin', '', 'admincndr@minsa.gob.ni', '', '2014-11-16 10:05:40', NULL, '2014-11-13 17:58:06', 'admin', '6c36dc262b0e44be5811c2296669fc65643aec9dcaa4a76501e0a9508b633fd01ee59a207f8c6d68');
INSERT INTO `inv_usuarios_roles` (`ROL`, `NOMBRE_USUARIO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`) VALUES ('ROLE_SUPER', 'admin', '0', '2016-06-07 10:47:12', 'admin');
INSERT INTO `inv_usuarios_roles` (`ROL`, `NOMBRE_USUARIO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`) VALUES ('ROLE_ADMIN', 'admin', '0', '2016-06-07 10:47:12', 'admin');
INSERT INTO `inv_usuarios_roles` (`ROL`, `NOMBRE_USUARIO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`) VALUES ('ROLE_CATALOG', 'admin', '0', '2017-03-26 10:25:29', 'admin');
INSERT INTO `inv_usuarios_roles` (`ROL`, `NOMBRE_USUARIO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`) VALUES ('ROLE_TRANS', 'admin', '0', '2017-03-26 10:25:29', 'admin');
INSERT INTO `inv_usuarios_roles` (`ROL`, `NOMBRE_USUARIO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`) VALUES ('ROLE_ADD_ALIC', 'admin', '0', '2017-03-26 10:25:29', 'admin');
INSERT INTO `inv_usuarios_roles` (`ROL`, `NOMBRE_USUARIO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`) VALUES ('ROLE_TEMP', 'admin', '0', '2017-03-30 12:17:14', 'admin');


INSERT INTO `inv_tipos_alicuota` (`CODIGO_TIPO_ALIC`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `NOMBRE_TIPO_ALIC`, `OBS_TIPO_ALIC`, `TIPO_MUESTRA`, `TEMPERATURA`, `USO_ALIC`, `VOLUMEN`) VALUES ('00000000-0586-034f-0000-00001a5dae20', '0', '2017-03-29 07:59:59', 'admin', '1b', '', 'SUERO', -80, 'PCR', 140);
INSERT INTO `inv_tipos_alicuota` (`CODIGO_TIPO_ALIC`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `NOMBRE_TIPO_ALIC`, `OBS_TIPO_ALIC`, `TIPO_MUESTRA`, `TEMPERATURA`, `USO_ALIC`, `VOLUMEN`) VALUES ('00000000-0586-034f-0000-00001a5e318d', '0', '2017-03-29 08:00:32', 'admin', '1c', '', 'SUERO', -80, 'PCR', 140);
INSERT INTO `inv_tipos_alicuota` (`CODIGO_TIPO_ALIC`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `NOMBRE_TIPO_ALIC`, `OBS_TIPO_ALIC`, `TIPO_MUESTRA`, `TEMPERATURA`, `USO_ALIC`, `VOLUMEN`) VALUES ('00000000-0586-034f-0000-00001a5e6239', '0', '2017-03-29 08:00:45', 'admin', '1d', '', 'SUERO', -80, 'PCR', 140);
INSERT INTO `inv_estudios` (`CODIGO_ESTUDIO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `NOMBRE_ESTUDIO`, `OBS_ESTUDIO`, `PATRON_ESTUDIO`) VALUES ('00000000-0586-034f-0000-00001a5efa26', '0', '2017-03-29 08:01:24', 'admin', 'Estudio Clínico', '', '^\\d{1,5}\\.[1|2|3|4|9]{1}[a|b|c|d|e|f|g|h|t|j|k|i|s|u|r]{1}$|^\\d{1,5}\\.[1|2|3|4|9]{1}[xx]{2}|\\d{1,5}\\.[1|9]{1}[rv1|rv2|rv3]{3}$|^\\d{1,5}\\.[1|2|3|9]{1}[q1|q2]{2}$');
INSERT INTO `inv_tipos_alicuotas_estudios` (`ESTUDIO`, `TIPO_ALICUOTA`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`) VALUES ('00000000-0586-034f-0000-00001a5efa26', '00000000-0586-034f-0000-00001a5dae20', '0', '2017-03-29 08:01:24', 'admin');
INSERT INTO `inv_tipos_alicuotas_estudios` (`ESTUDIO`, `TIPO_ALICUOTA`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`) VALUES ('00000000-0586-034f-0000-00001a5efa26', '00000000-0586-034f-0000-00001a5e318d', '0', '2017-03-29 08:01:24', 'admin');
INSERT INTO `inv_tipos_alicuotas_estudios` (`ESTUDIO`, `TIPO_ALICUOTA`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`) VALUES ('00000000-0586-034f-0000-00001a5efa26', '00000000-0586-034f-0000-00001a5e6239', '0', '2017-03-29 08:01:24', 'admin');

INSERT INTO `inv_centros` (`CODIGO_CENTRO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `DIRECCION_CENTRO`, `CONTACTO_CENTRO`, `EMAIL_CENTRO`, `NOMBRE_CENTRO`, `OBS_CENTRO`, `TELEFONO_CENTRO`) VALUES ('00000000-0586-034f-0000-00001a5fe4e5', '0', '2017-03-29 08:02:24', 'admin', 'Managu', 'Dr. Angel Balmaseda', 'abalmaseda@minsa.gob.ni', 'Centro Nacional de Diagnóstico y Referencia', '', '50522897227');
INSERT INTO `inv_cuartos` (`CODIGO_CUARTO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`, `NOMBRE_CUARTO`, `OBS_CUARTO`, `CODIGO_CENTRO`) VALUES ('00000000-0586-034f-0000-00001a60b025', '0', '2017-03-29 08:03:16', 'admin', 'Cuarto frío 1', '', '00000000-0586-034f-0000-00001a5fe4e5');

INSERT INTO `inv_usuarios_centros` (`CENTRO`, `NOMBRE_USUARIO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`) VALUES ('00000000-0586-034f-0000-00001a5fe4e5', 'admin', '0', '2017-03-29 09:03:19', 'admin');
INSERT INTO `inv_usuarios_centros` (`CENTRO`, `NOMBRE_USUARIO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`) VALUES ('00000000-0586-034f-0000-00001a5fe4e5', 'william', '0', '2017-03-29 09:03:19', 'admin');
INSERT INTO `inv_estudios_centros` (`CENTRO`, `ESTUDIO`, `PASIVO`, `FECHA_REGISTRO`, `USUARIO_REGISTRO`) VALUES ('00000000-0586-034f-0000-00001a5fe4e5', '00000000-0586-034f-0000-00001a5efa26', '0', '2017-03-29 09:03:19', 'admin');

/*Pagina denegado*/
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( '403.denied', 'Acceso denegado','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( '403.deniedmessage', 'Lo sentimos','0','0',0);
/*Pagina no encontrado*/
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( '404.notfound', 'No encontrado','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( '404.notfoundmessage', 'Lo sentimos','0','0',0);
/*Acciones, todas las paginas*/
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'actions', 'Acciones','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'add', 'Agregar','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'save', 'Guardar','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'edit', 'Editar','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'cancel', 'Cancelar','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'disable', 'Deshabilitar','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'enable', 'Habilitar','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'unlock', 'Desbloquear','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lock', 'Bloquear','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'export', 'Exportar','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'ok', 'Aceptar','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'search', 'Buscar','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'confirm', 'Confirmar','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'new', 'Nuevo','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `catPasive`, `es`, `en`, `isCat`, `orden`) VALUES ('delete', '0', 'Eliminar', 'Delete','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'remove', 'Quitar','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'please.enter', 'Favor ingresar','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'end', 'Finalizar','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'select', 'Seleccionar','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'change', 'Cambiar','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'parameter', 'parámetro','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'dateRange', 'Rango de fechas','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'noResults', 'No hay registros!','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'position', 'Ubicación','0','0',0);
/*Menu*/
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'home', 'Inicio','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'addalic', 'Ingresar muestra','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'newalicsug', 'Usar sugerencia','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'newalicman', 'Elegir ubicación','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'alics', 'Muestras','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'searchalic', 'Buscar muestra','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'usealic', 'Usar muestra','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'sendalic', 'Enviar muestra','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'translation', 'Mensajes','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'temprecord', 'Temperaturas','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'catalog', 'Catálogos','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'catalogalic', 'Tipos Alicuota','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'catalogall', 'Catálogos Generales','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'admin', 'Administración','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'adminusers', 'Usuarios','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'adminequips', 'Equipos','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'adminracks', 'Racks','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'adminboxes', 'Cajas','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'super', 'Configuración','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'supercenters', 'Centros','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'superstudies', 'Estudios','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'superrooms', 'Cuartos','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'profile', 'Perfil','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'changepass', 'Cambiar contraseña..','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'logout', 'Salir','0','0',0);
/*Mensajes generales, todas las paginas*/
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'enabled', 'Habilitado','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'locked', 'Bloqueado','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'form.errors', 'Tiene errores en su formulario. Favor verificar!','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'process.errors', 'Han ocurrido errores en el proceso!','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'process.success', 'El proceso se ha completado exitosamente!','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'date', 'Fecha','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'not', 'Notificación','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'notenabled', 'Deshabilitado','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'notlocked', 'Desbloqueado','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'seconds', 'segundos','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'session.expiring', 'Su sesión está a punto de expirar!','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'session.expiring.confirm', 'Quiere continuar con su sesión?','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'session.expiring.time', 'Su sesión se cerrará en','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'session.keep', 'Mantener sesión','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'datavalidationerror', 'Error al actualizar los datos!','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'total', 'Total','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'dx', 'Mapa','0','0',0);

/*Metadata*/
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'createdBy', 'Creado por','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'dateCreated', 'Fecha creación','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'modifiedBy', 'Modificado por','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'dateModified', 'Fecha de modificación','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'active', 'Activo','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'addedBy', 'Agregado por','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'dateAdded', 'Fecha','0','0',0);

/*login*/
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login', 'Ingresar','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.accountExpired', 'Cuenta de usuario ha expirado!','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.accountLocked', 'Cuenta de usuario está bloqueada!','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.accountNotLocked', 'Cuenta de usuario está desbloqueada!','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.badCredentials', 'Nombre de usuario o contraseña incorrectos!','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.credentialsExpired', 'Credenciales de usuario han expirado!','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.footer', 'Instituto de Ciencias Sostenibles','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.maxSessionsOut', 'Tiene una sesión activa! No puede crear otra!','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.msg', 'Por favor ingresar su nombre de usuario y contraseña','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.page', 'Página de ingreso','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.password', 'Contraseña','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.userEnabled', 'Usuario esta activo!','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.userDisabled', 'Usuario esta inactivo!','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'login.username', 'Nombre de usuario','0','0',0);

/*forzar cambio contraseña*/
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'credentials.expired', 'Su contraseña ha caducado','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'pass.updated', 'Su contraseña ha sido actualizada','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'password.repeat', 'Repita la contraseña','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'Pattern.password.format', 'Al menos 8 caracteres combinando mayúsculas, minúsculas, números y caracteres especiales','0','0',0);

/*Plantilla*/
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'heading', 'Control de muestras','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'title', 'Inventario','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'footer', 'Instituto de Ciencias Sostenibles','0','0',0);

/*pagina principal*/
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'dashboard', 'Panel de control','0','0',0);

/*Usuarios*/
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'username', 'Usuario','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'userdesc', 'Descripción','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'useremail', 'Correo','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'userlock', 'Bloqueado','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'usercred', 'Contraseña vencida','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'userexp', 'Cuenta vencida','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'userroles', 'Roles','0','0',0);

/*Centros*/
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'centerCode', 'Código centro','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'centerName', 'Nombre centro','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'centerContact', 'Nombre contacto','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'centerAddress', 'Ubicación centro','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'centerPhoneNumber', 'Número de teléfono','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'centerEmail', 'Correo electrónico','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'center.disabled', 'Centro deshabilitado','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'center.enabled', 'Centro habilitado','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'centerObs', 'Observaciones','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'selectcenter', 'Favor seleccionar centro de trabajo para continuar ...','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'centerNotEmpty', 'Centro no esta vacío','0','0',0);

/*Estudios*/
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'studyCode', 'Código estudio','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'studyName', 'Nombre estudio','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'studyPattern', 'Patrón estudio','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'study.disabled', 'Estudio deshabilitado','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'study.enabled', 'Estudio habilitado','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'studyObs', 'Observaciones','0','0',0);

/*Cuartos*/
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'roomCode', 'Código cuarto','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'roomName', 'Nombre cuarto','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'room.disabled', 'Cuarto deshabilitado','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'room.enabled', 'Cuarto habilitado','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'roomObs', 'Observaciones','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'roomNotEmpty', 'Cuarto no esta vacío','0','0',0);

/*Equipos*/
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'equipCode', 'Código equipo','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'equipName', 'Nombre equipo','0','0',0);

INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'equip.disabled', 'Equipo deshabilitado','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'equip.enabled', 'Cuarto habilitado','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'equipObs', 'Observaciones','0','0',0);

INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'equipRoom', 'Cuarto','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'equipTempMin', 'Temperatura mínima','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'equipTempMax', 'Temperatura máxima','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'equipUse', 'Uso del equipo','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'equipType', 'Tipo de equipo','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'equipCapacity', 'Capacidad','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'equipRows', 'Número filas','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'equipColumns', 'Número columnas','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'equipSerie', 'Serie','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'equipBrand', 'Marca','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'equipModel', 'Modelo','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'equipResp', 'Responsable','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'equipPriority', 'Prioridad','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'equipNotEmpty', 'Equipo no esta vacío','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'equipDuplicated', 'Nombre de equipo ya esta en uso en este centro','0','0',0);

/*Racks*/
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'rackCode', 'Código rack','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'rackName', 'Nombre rack','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'rack.disabled', 'Rack deshabilitado','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'rack.enabled', 'Rack habilitado','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'rackObs', 'Observaciones','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'rackEquip', 'Equipo','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'rackPosition', 'Posición en el equipo','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'rackCapacity', 'Capacidad','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'rackRows', 'Filas','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'rackColumns', 'Columnas','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'rackPriority', 'Prioridad','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'rackNotEmpty', 'Rack no esta vacío','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'rackDuplicated', 'Nombre de rack ya esta en uso en este centro','0','0',0);


/*Boxes*/
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'boxCode', 'Código caja','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'boxName', 'Nombre caja','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'box.disabled', 'Caja deshabilitada','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'box.enabled', 'Caja habilitada','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'boxObs', 'Observaciones','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'boxRack', 'Rack','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'boxStudy', 'Estudio','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'boxAlicType', 'Tipo de alicuota','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'boxAlicUse', 'Uso de alicuota','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'boxUse', 'Uso de la caja','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'boxTemp', 'Temperatura','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'boxType', 'Tipo','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'boxResultType', 'Tipo de resultado','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'boxPosition', 'Posición en el rack','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'boxRows', 'Número filas','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'boxColumns', 'Número columnas','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'boxCapacity', 'Capacidad','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'boxPriority', 'Prioridad','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'boxNotEmpty', 'Caja no esta vacía','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'boxDuplicated', 'Nombre de caja ya esta en uso en este centro','0','0',0);

/*Alicuotas*/
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'aliCode', 'Código alicuota','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'aliBox', 'Caja','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'aliPosition', 'Posición','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'aliVol', 'Volumen','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'aliRes', 'Resultado','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'aliCond', 'Condición','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'aliSep', 'Separación','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'aliObs', 'Observaciones','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'aliDuplicated', 'Esta alicuota ya está en el inventario','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'ali.disabled', 'Alicuota anulada','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'ali.enabled', 'Alicuota activa','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'aliNotPattern', 'Formato de alicuota no válido','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'aliNotInList', 'Alicuota no existe en este estudio','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'regExpInv', 'Expresión de validación incorrecta de este estudio','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'aliquotData', 'Datos alicuota','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'alicDuplicated', 'Código de Alicuota ya se encuentra registrado','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'available', 'Disponible','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'notAvailable', 'No Disponible','0','0',0);


/*Tipos de Alicuotas*/
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'alicTypeCode', 'Código tipo alicuota','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'alicTypeName', 'Tipo de alicuota','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'alicTypeUse', 'Uso tipo alicuota','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'alicTypeTemp', 'Temperatura','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'alicTypeVol', 'Volumen','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'alicTypeSample', 'Tipo de muestra','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'alicTypeObs', 'Observaciones','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'alictype.disabled', 'Tipo de alicuota anulada','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'alictype.enabled', 'Tipo de alicuota activa','0','0',0);

/*Mensajes*/
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'messageKey', 'Código mensaje','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'spanish', 'Español','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'english', 'Inglés','0','0',0);

/*Registro de temperatura*/
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'tempCode', 'Código registro','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'tempDate', 'Fecha de registro','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'tempObs', 'Observaciones','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'tempTemp', 'Temperatura','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'tempEquip', 'Equipo','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'malEstado', 'En mal estado','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'fueraRango', 'Fuera de rango','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'temp.disabled', 'Registro anulado','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'temp.enabled', 'Registro activo','0','0',0);

/*Accesos*/
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'access', 'Accesos de usuario','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'lastAccess', 'Ultimo acceso','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'dateCredentials', 'Ultimo cambio de contraseña','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'session', 'Id de sesión','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'ipaddress', 'Dirección IP','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'logindate', 'Fecha ingreso','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'logoutdate', 'Fecha salida','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'logouturl', 'URL salida','0','0',0);
/*Audit trail*/
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'audittrail', 'Bitácora de cambios','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'entityClass', 'Clase','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'entityName', 'Nombre','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'entityProperty', 'Propiedad','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'entityPropertyOldValue', 'Valor anterior','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'entityPropertyNewValue', 'Nuevo valor','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'class ni.org.ics.lab.inventario.domain.Center', 'Centro','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'class ni.org.ics.lab.inventario.domain.Study', 'Estudio','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'class ni.org.ics.lab.inventario.domain.Room', 'Cuarto','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'class ni.org.ics.lab.inventario.domain.relationships.UserCenter', 'Centro del usuario','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'class ni.org.ics.lab.inventario.domain.relationships.StudyCenter', 'Estudio del centro','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'class ni.org.ics.lab.inventario.domain.relationships.AlicTypeStudy', 'Tipo alicuota del estudio','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'class ni.org.ics.lab.inventario.users.model.Authority', 'Rol de usuario','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'class ni.org.ics.lab.inventario.users.model.UserSistema', 'Usuario','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'class ni.org.ics.lab.inventario.domain.Equipment', 'Equipo','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'class ni.org.ics.lab.inventario.domain.Rack', 'Rack','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'class ni.org.ics.lab.inventario.domain.Box', 'Caja','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'class ni.org.ics.lab.inventario.domain.Aliquot', 'Alicuota','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'class ni.org.ics.lab.inventario.domain.AliquotType', 'Tipo de Alicuota','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'class ni.org.ics.lab.inventario.domain.TempRecord', 'Registro de temperatura','0','0',0);



/*Roles*/
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'ROLE_SUPER', 'Configuración','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'ROLE_ADMIN', 'Administrador','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'ROLE_TRANS', 'Mensajes','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'ROLE_CATALOG', 'Catálogos','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'ROLE_ADD_ALIC', 'Ingresar muestra','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'ROLE_EDIT_ALIC', 'Editar muestra','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'ROLE_DEL_ALIC', 'Anular muestra','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'ROLE_USE_ALIC', 'Usar muestra','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'ROLE_SEND_ALIC', 'Enviar muestra','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'ROLE_TEMP', 'Temperaturas','0','0',0);

/*theme*/
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'theme.blue', 'Azul','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'theme.boxed', 'En caja','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'theme.color', 'Color','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'theme.dark', 'Negro','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'theme.default', 'Movible','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'theme.fixed', 'Fijo','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'theme.fluid', 'Fuido','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'theme.footer', 'Pie de pagina','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'theme.grey', 'Gris','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'theme.header', 'Encabezado','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'theme.layout', 'Diseño','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'theme.left', 'Izquierda','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'theme.light', 'Claro','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'theme.red', 'Rojo','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'theme.right', 'Derecha','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'theme.sbposition', 'Posición Barra lateral','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'theme.sidebar', 'Barra lateral','0','0',0);

/*Catalogos*/
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'snd', 'Catálogo Si, No, Desconocido','0','1',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catKey`, `catPasive`, `catRoot`, `isCat`, `orden`) VALUES ( 'no', 'N','0','0','snd','0',2);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catKey`, `catPasive`, `catRoot`, `isCat`, `orden`) VALUES ( 'yes', 'S','1','0','snd','0',1);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catKey`, `catPasive`, `catRoot`, `isCat`, `orden`) VALUES ( 'unk', 'D','9','0','snd','0',3);


/*Tipo Equipo*/
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'equipTypeCat', 'Catálogo Tipo de Equipo','0','1',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catKey`, `catPasive`, `catRoot`, `isCat`, `orden`) VALUES ( 'FREE_HOR', 'Freezer Horizontal','FREE_HOR','0','equipTypeCat','0',1);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catKey`, `catPasive`, `catRoot`, `isCat`, `orden`) VALUES ( 'FREE_VERT', 'Freezer Vertical','FREE_VERT','0','equipTypeCat','0',2);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catKey`, `catPasive`, `catRoot`, `isCat`, `orden`) VALUES ( 'TAN', 'Tanque','TAN','0','equipTypeCat','0',3);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catKey`, `catPasive`, `catRoot`, `isCat`, `orden`) VALUES ( 'CON', 'Congelador','CON','0','equipTypeCat','0',4);
/*Uso Equipo*/
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'equipUseCat', 'Catálogo Uso de Equipo','0','1',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catKey`, `catPasive`, `catRoot`, `isCat`, `orden`) VALUES ( 'ARCHIVO', 'Archivo','ARCHIVO','0','equipUseCat','0',1);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catKey`, `catPasive`, `catRoot`, `isCat`, `orden`) VALUES ( 'PCR', 'PCR','PCR','0','equipUseCat','0',2);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catKey`, `catPasive`, `catRoot`, `isCat`, `orden`) VALUES ( 'AV', 'Aislamiento Viral','AV','0','equipUseCat','0',3);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catKey`, `catPasive`, `catRoot`, `isCat`, `orden`) VALUES ( 'SERO', 'Serología','SERO','0','equipUseCat','0',4);

/*Tipo de muestra*/
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'tipoMuestraCat', 'Catálogo Tipo de Muestra','0','1',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catKey`, `catPasive`, `catRoot`, `isCat`, `orden`) VALUES ( 'SUERO', 'Suero','SUERO','0','tipoMuestraCat','0',1);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catKey`, `catPasive`, `catRoot`, `isCat`, `orden`) VALUES ( 'PLASMA', 'Plasma','PLASMA','0','tipoMuestraCat','0',2);

/*Tipo de resultado*/
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'tipoResultadoCat', 'Catálogo Tipo de Resultado','0','1',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catKey`, `catPasive`, `catRoot`, `isCat`, `orden`) VALUES ( 'POS', 'Positivo','POS','0','tipoResultadoCat','0',1);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catKey`, `catPasive`, `catRoot`, `isCat`, `orden`) VALUES ( 'NEG', 'Negativo','NEG','0','tipoResultadoCat','0',2);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catKey`, `catPasive`, `catRoot`, `isCat`, `orden`) VALUES ( 'SR', 'Sin Resultado','SR','0','tipoResultadoCat','0',3);

/* Condiciones de muestra */
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catKey`, `catPasive`, `catRoot`, `isCat`, `orden`) VALUES ( 'c_list', 'Contaminada','CLIST','0','condicionCat','0',1);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catKey`, `catPasive`, `catRoot`, `isCat`, `orden`) VALUES ( 'h_list', 'Hemolizada','HLIST','0','condicionCat','0',2);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catKey`, `catPasive`, `catRoot`, `isCat`, `orden`) VALUES ( 'i_list', 'Icterico','ILIST','0','condicionCat','0',3);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catKey`, `catPasive`, `catRoot`, `isCat`, `orden`) VALUES ( 'l_list', 'Lipemica','LLIST','0','condicionCat','0',4);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catKey`, `catPasive`, `catRoot`, `isCat`, `orden`) VALUES ( 'lc_list', 'Lipemica y contaminada','LCLIST','0','condicionCat','0',5);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catKey`, `catPasive`, `catRoot`, `isCat`, `orden`) VALUES ( 'lh_list', 'Lipemica y hemolizada','LHLIST','0','condicionCat','0',6);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catKey`, `catPasive`, `catRoot`, `isCat`, `orden`) VALUES ( 'o_list', 'Optima','OLIST','0','condicionCat','0',7);


/* Uso de alicuotas */

INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'alic_use_menu', 'Uso de alicuota','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'purpose', 'Propósito','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'used_volume', 'Volumen usado','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'all_alic_used', 'Se usó toda la alícuota','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'exceeded_volume', 'El volumen ingresado supera el volumen de la muestra seleccionada','0','0',0);


/* Salida de alicuotas */
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'alic_output', 'Salida de alicuotas','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'pos_neg', 'Positivo/Negativo','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'repeated_alic', 'Alicuota ya se encuentra agregada.','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'enic', 'ENIC','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'sendDate', 'Fecha de envío','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'boxNum', 'No. Caja','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'transportation', 'Medio','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'destination', 'Destino','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'requestBy', 'Solicitado por','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'containerNum', 'No. Contenedor','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'searchManager', 'Resp. Búsqueda','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'approveBy', 'Aprobado por','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'carrier', 'Transporta','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'packingManager', 'Resp. Embalaje','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'process', 'Procesar','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'select_file', 'Seleccionar archivo','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'process', 'Procesar','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'import_file', 'Importar Archivo','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'msg_yes', 'Si','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'msg_no', 'No','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'conf_msg', 'Mensaje de Confirmación','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'msg_content', 'No se han obtenido resultados de la búsqueda. ¿Desea agregar la muestra?','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'alic_found', 'Alícuotas encontradas.','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'alic_not_found', 'Alícuotas no encontradas.','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'added_alic', 'Alicuotas agregadas.','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'repeated_alics', 'Registros encontrados en la tabla.','0','0',0);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catPasive`, `isCat`, `orden`) VALUES ( 'transfer_alic', 'Transferir Alicuota','0','0',0);






/* Medio */
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catKey`, `catPasive`, `catRoot`, `isCat`, `orden`) VALUES ( 'dry_ice', 'Termo con hielo seco','MVALUE1','0','medioCat','0',1);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catKey`, `catPasive`, `catRoot`, `isCat`, `orden`) VALUES ( 'refrigerant', 'Termo con refrigerante','MVALUE2','0','medioCat','0',2);
INSERT INTO `inv_mensajes` (`messageKey`, `es`, `catKey`, `catPasive`, `catRoot`, `isCat`, `orden`) VALUES ( 'liquid_nitro', 'Tanque nitrógeno líquido','MVALUE3','0','medioCat','0',3);




