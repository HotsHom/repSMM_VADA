from django.shortcuts import render
from django.http import HttpResponseRedirect
from .models import VkToken, Post
from .serializers import TokenSerializer, PostSerializer
from rest_framework.response import Response
from rest_framework.generics import get_object_or_404
from rest_framework import viewsets, generics
from django.db.migrations import serializer
from rest_framework_swagger import renderers
from rest_framework.schemas import SchemaGenerator
from rest_framework.views import APIView

class TokenViewSet(viewsets.ModelViewSet):
    queryset = VkToken.objects.all()
    serializer_class = TokenSerializer

class PostViewSet(viewsets.ModelViewSet):
    queryset = Post.objects.all()
    serializer_class = PostSerializer

class PurchaseList(generics.RetrieveAPIView):
    queryset = VkToken.objects.all()
    serializer_class = TokenSerializer
    lookup_field = 'user_id'

    def get_object(self):
        queryset = self.get_queryset()
        obj = get_object_or_404(queryset, user_id=self.kwargs['user_id'])
        return obj