from __future__ import absolute_import, unicode_literals
import os
from celery import Celery

# set the default Django settings module for the 'celery' program.
os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'smm_backend.settings')


app = Celery('smm_backend')
app = Celery('smm_backend', broker='redis://127.0.0.1:6379/0')
# Using a string here means the worker doesn't have to serialize
# the configuration object to child processes.
# - namespace='CELERY' means all celery-related configuration keys
#   should have a `CELERY_` prefix.

app.config_from_object('django.conf:settings', namespace='CELERY')
os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'myproj.settings')
os.environ.setdefault('FORKED_BY_MULTIPROCESSING', '1')



# Load task modules from all registered Django app configs.

app.autodiscover_tasks()

@app.task(bind=True)
def debug_task(self):
    print('Request: {0!r}'.format(self.request))

app.conf.beat_schedule = {
    #name of the scheduler

    'add-every-2-seconds': {
        # task name which we have created in tasks.py

        'task': 'post',  
        # set the period of running
        
        'schedule': 1.0,
         # set the args 
         
        'args': () 
    },
    #name of the scheduler

    # 'print-name-every-5-seconds': {  
    #     # task name which we have created in tasks.py

    #     'task': 'print_msg_with_name',  
        
    #     # set the period of running

    #     'schedule': 10.0,  
    #     # set the args

    #    'args': ("DjangoPY", )  
    # },
}
